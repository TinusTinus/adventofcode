package nl.mvdr.adventofcode.adventofcode2021.day16

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

fun decode(hexadecimal: String): Packet {
    val (packet, remaining) = decodePacket(toBinary(hexadecimal))
    if (remaining.any { it != '0' }) {
        throw IllegalArgumentException("Unexpected trailing characters found: $remaining")
    }
    return packet
}

/**
 * Converts the given hexadecimal string to binary format.
 */
fun toBinary(hexadecimal: String): String = hexadecimal.map { it.toString().toInt(16) }
    .map { it.toString(2) }
    .joinToString(separator = "") { it.padStart(4, '0') }

/**
 * Decodes a packet based on the given string represenation of a [binary] value.
 * Returns a pair containing the decoded packet and the remaining contents of the string.
 */
private fun decodePacket(binary: String): Pair<Packet, String> {
    logger.debug { "Decoding packet: $binary" }

    var remaining = binary

    val version = decodeNumber(remaining.substring(0 until 3))
    remaining = remaining.substring(3)
    logger.debug { "Version: $version, remaining binary string: $remaining" }

    val typeId = decodeNumber(remaining.substring(0 until 3))
    remaining = remaining.substring(3)
    logger.debug { "Type id: $typeId, remaining binary string: $remaining" }

    val result = when (typeId) {
        4 -> {
            var keepReading = true
            while (keepReading) {
                keepReading = when(val keepReadingValue = decodeNumber(remaining.substring(0 until 1))) {
                    0 -> false
                    1 -> true
                    else -> throw IllegalStateException("Unexpected keep reading value: $keepReadingValue")
                }
                remaining = remaining.substring(5)
            }
            LiteralValuePacket(version)
        }
        else -> {
            val lengthTypeId = decodeNumber(remaining.substring(0 until 1))
            remaining = remaining.substring(1)
            logger.debug { "Length type id: $lengthTypeId, remaining binary string: $remaining" }

            val subPackets = when(lengthTypeId) {
                0 -> {
                    val totalLength = decodeNumber(remaining.substring(0 until 15))
                    remaining = remaining.substring(15)
                    logger.debug { "Total length: $totalLength, remaining binary string: $remaining" }

                    val decodedSubPackets = decodeSubPackets(remaining.substring(0 until totalLength), null)
                    remaining = remaining.substring(totalLength)

                    decodedSubPackets.first
                }
                1 -> {
                    val totalPackets = decodeNumber(remaining.substring(0 until 11))
                    remaining = remaining.substring(11)

                    val decodedSubPackets = decodeSubPackets(remaining, totalPackets)
                    remaining = decodedSubPackets.second

                    decodedSubPackets.first
                }
                else -> throw IllegalStateException("Unexpected length type id: $lengthTypeId")
            }
            OperatorPacket(version, subPackets)
        }
    }

    logger.debug { "Decoded packet: $result" }

    return Pair(result, remaining)
}

/**
 * Decodes the first [length] characters of the given [binary] string representation as a number.
 * Returns a pair containing the decoded number and the remaining contents of the string.
 */
private fun decodeNumber(binary: String, length: Int) = Pair(binary.substring(0 until length).toInt(2), binary.substring(length))

private fun decodeNumber(binary: String) = binary.toInt(2)

private fun decodeSubPackets(binary: String, numberOfPackets: Int?): Pair<List<Packet>, String> {
    var remaining = binary
    val packets = mutableListOf<Packet>()
    while (remaining.isNotEmpty() && (numberOfPackets == null || packets.size < numberOfPackets)) {
        val decodedSubPacket = decodePacket(remaining)
        packets.add(decodedSubPacket.first)
        remaining = decodedSubPacket.second
    }
    return Pair(packets, remaining)
}