package com.adshield.protection

import java.util.Locale

/**
 * Parses the single question section of a DNS query message.
 *
 * This is deliberately not a full DNS packet parser: callers must validate the
 * surrounding IP/UDP packet and must not forward or answer a packet based only
 * on this object. Limits are conservative to keep parsing bounded.
 */
internal object DnsQuestionParser {
    private const val HEADER_LENGTH = 12
    private const val MAX_DNS_MESSAGE = 4096
    private const val MAX_NAME_WIRE_LENGTH = 255

    data class Question(
        val transactionId: Int,
        val flags: Int,
        val domain: String,
        val queryType: Int,
        val queryClass: Int,
        val questionEndOffset: Int,
    )

    fun parse(message: ByteArray): Question {
        require(message.size in HEADER_LENGTH..MAX_DNS_MESSAGE) { "DNS message length is invalid" }

        val transactionId = u16(message, 0)
        val flags = u16(message, 2)
        require((flags and 0x8000) == 0) { "DNS message is a response, not a query" }
        require(u16(message, 4) == 1) { "Exactly one DNS question is required" }

        val parsedName = readName(message, HEADER_LENGTH)
        val typeOffset = parsedName.nextOffset
        require(typeOffset <= message.size - 4) { "Truncated DNS question fields" }

        return Question(
            transactionId = transactionId,
            flags = flags,
            domain = parsedName.name.lowercase(Locale.ROOT),
            queryType = u16(message, typeOffset),
            queryClass = u16(message, typeOffset + 2),
            questionEndOffset = typeOffset + 4,
        )
    }

    private data class ParsedName(val name: String, val nextOffset: Int)

    private fun readName(message: ByteArray, startOffset: Int): ParsedName {
        require(startOffset in HEADER_LENGTH until message.size) { "DNS name starts outside message" }

        val labels = ArrayList<String>()
        var cursor = startOffset
        var nextOffset = -1
        var wireLength = 1

        while (true) {
            require(cursor < message.size) { "Truncated DNS name" }
            val length = message[cursor].toInt() and 0xff

            when (length and 0xc0) {
                0x00 -> {
                    cursor++
                    if (length == 0) {
                        if (nextOffset == -1) nextOffset = cursor
                        break
                    }

                    require(length <= 63) { "DNS label exceeds 63 bytes" }
                    require(cursor <= message.size - length) { "Truncated DNS label" }
                    wireLength += length + 1
                    require(wireLength <= MAX_NAME_WIRE_LENGTH) { "DNS name exceeds 255 wire bytes" }

                    val labelBytes = message.copyOfRange(cursor, cursor + length)
                    require(labelBytes.all { (it.toInt() and 0xff) in 0x21..0x7e }) {
                        "Non-ASCII or control byte in DNS label"
                    }
                    val label = String(labelBytes, Charsets.US_ASCII)
                    require(!label.contains('.')) { "Dot byte inside DNS label" }
                    labels.add(label)
                    cursor += length
                }

                0xc0 -> {
                    // QNAME is the first domain name in a DNS message. It has no
                    // earlier domain name to reference; a pointer into this same
                    // QNAME would create a cycle. Reject compression here.
                    throw IllegalArgumentException("Compression pointer is not valid in the first DNS question name")
                }

                else -> throw IllegalArgumentException("Reserved DNS label encoding")
            }
        }

        require(labels.isNotEmpty()) { "Root-only DNS question is not accepted" }
        val name = labels.joinToString(".")
        require(name.length <= 253) { "DNS presentation name exceeds 253 characters" }
        return ParsedName(name, nextOffset)
    }

    private fun u16(bytes: ByteArray, offset: Int): Int {
        require(offset >= 0 && offset <= bytes.size - 2) { "Truncated DNS integer" }
        return ((bytes[offset].toInt() and 0xff) shl 8) or
            (bytes[offset + 1].toInt() and 0xff)
    }
}
