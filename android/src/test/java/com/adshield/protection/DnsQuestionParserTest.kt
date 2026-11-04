package com.adshield.protection

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.random.Random

class DnsQuestionParserTest {
    private fun query(name: ByteArray, flags: Int = 0x0100, qdCount: Int = 1): ByteArray {
        val header = byteArrayOf(
            0x12, 0x34,
            (flags ushr 8).toByte(), flags.toByte(),
            (qdCount ushr 8).toByte(), qdCount.toByte(),
            0, 0, 0, 0, 0, 0
        )
        return header + name + byteArrayOf(0, 1, 0, 1)
    }

    @Test
    fun parsesARecordQuestion() {
        val packet = query(byteArrayOf(
            0x07, 0x65, 0x78, 0x61, 0x6d, 0x70, 0x6c, 0x65,
            0x03, 0x63, 0x6f, 0x6d, 0x00
        ))
        val result = DnsQuestionParser.parse(packet)
        assertEquals(0x1234, result.transactionId)
        assertEquals("example.com", result.domain)
        assertEquals(1, result.queryType)
        assertEquals(1, result.queryClass)
        assertEquals(packet.size, result.questionEndOffset)
    }

    @Test
    fun normalizesAsciiDomainCase() {
        val packet = query(byteArrayOf(
            0x03, 0x57, 0x77, 0x57, 0x00
        ))
        assertEquals("www", DnsQuestionParser.parse(packet).domain)
    }

    @Test
    fun rejectsCompressionPointerLoop() {
        val packet = query(byteArrayOf(0xc0.toByte(), 0x0c))
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsForwardCompressionPointer() {
        val packet = query(byteArrayOf(0xc0.toByte(), 0x20))
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsTruncatedCompressionPointer() {
        val packet = byteArrayOf(
            0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0,
            0xc0.toByte()
        )
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsReservedLabelEncoding() {
        val packet = query(byteArrayOf(0x40, 0, 0))
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsResponsePackets() {
        val packet = query(byteArrayOf(0x01, 0x61, 0))
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsQuestionCountOtherThanOne() {
        val packet = query(byteArrayOf(0x01, 0x61, 0), qdCount = 2)
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsLabelLongerThan63Bytes() {
        val packet = query(byteArrayOf(0x40, 0) + ByteArray(64) { 0x61 })
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsNonAsciiLabelBytes() {
        val packet = query(byteArrayOf(0x01, 0xff.toByte(), 0))
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsMessagesLargerThanConfiguredBound() {
        val packet = ByteArray(4097)
        packet[5] = 1
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun malformedInputCorpusNeverEscapesAsUnexpectedException() {
        val random = Random(0xAD5A1)
        repeat(1000) {
            val size = random.nextInt(0, 128)
            val packet = ByteArray(size) { random.nextInt(0, 256).toByte() }
            try {
                DnsQuestionParser.parse(packet)
            } catch (_: IllegalArgumentException) {
                // Expected for malformed or unsupported input.
            }
        }
    }
}
