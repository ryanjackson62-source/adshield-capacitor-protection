package com.adshield.protection

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class DnsQuestionParserTest {
    @Test
    fun parsesARecordQuestion() {
        val packet = byteArrayOf(
            0x12, 0x34, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x07, 0x65, 0x78, 0x61, 0x6d, 0x70, 0x6c, 0x65,
            0x03, 0x63, 0x6f, 0x6d, 0x00,
            0x00, 0x01, 0x00, 0x01,
        )
        val result = DnsQuestionParser.parse(packet)
        assertEquals(0x1234, result.transactionId)
        assertEquals("example.com", result.domain)
        assertEquals(1, result.queryType)
        assertEquals(1, result.queryClass)
        assertEquals(packet.size, result.questionEndOffset)
    }

    @Test
    fun rejectsCompressionPointerLoop() {
        val packet = byteArrayOf(
            0x00, 0x01, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0xc0.toByte(), 0x0c, 0x00, 0x01, 0x00, 0x01,
        )
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }

    @Test
    fun rejectsTruncatedQuestion() {
        val packet = byteArrayOf(
            0x00, 0x01, 0x01, 0x00, 0x00, 0x01, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,
            0x07, 0x65, 0x78,
        )
        assertThrows(IllegalArgumentException::class.java) {
            DnsQuestionParser.parse(packet)
        }
    }
}
