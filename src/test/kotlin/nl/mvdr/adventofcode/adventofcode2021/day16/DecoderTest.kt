package nl.mvdr.adventofcode.adventofcode2021.day16

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class DecoderTest {

    @ParameterizedTest
    @MethodSource
    fun testToBinary(binary: String, hexadecimal: String) = assertEquals(binary, toBinary(hexadecimal))

    @Test
    fun testDecodeLiteralPacket() {
        val input = "D2FE28"

        val packet = decode(input)

        assertTrue(packet is LiteralValuePacket)
        assertEquals(6, packet.version)
        assertEquals(6, packet.totalVersionNumber())
    }

    @Test
    fun testDecodeOperatorPacketWithLength() {
        val input = "38006F45291200"

        val packet = decode(input)

        assertTrue(packet is OperatorPacket)
        assertEquals(1, packet.version)
        assertEquals(2, packet.subPackets.size)
        packet.subPackets.forEach { assertTrue(it is LiteralValuePacket) }
    }

    @Test
    fun testDecodeOperatorPacketWithCount() {
        val input = "EE00D40C823060"

        val packet = decode(input)

        assertTrue(packet is OperatorPacket)
        assertEquals(7, packet.version)
        assertEquals(3, packet.subPackets.size)
        packet.subPackets.forEach { assertTrue(it is LiteralValuePacket) }
    }

    companion object {
        @JvmStatic
        fun testToBinary(): List<Arguments> = listOf(
            Arguments.of("110100101111111000101000", "D2FE28"),
            Arguments.of("00111000000000000110111101000101001010010001001000000000", "38006F45291200"),
            Arguments.of("11101110000000001101010000001100100000100011000001100000", "EE00D40C823060")
        )
    }

}