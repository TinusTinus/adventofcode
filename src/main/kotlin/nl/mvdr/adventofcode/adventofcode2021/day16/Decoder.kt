package nl.mvdr.adventofcode.adventofcode2021.day16

fun decode(hexadecimal: String): Packet = decodeBinary(toBinary(hexadecimal))

/**
 * Converts the given hexadecimal string to binary format.
 */
fun toBinary(hexadecimal: String): String = hexadecimal.map { it.toString().toInt(16) }
    .map { it.toString(2) }
    .joinToString(separator = "") { it.padStart(4, '0') }

private fun decodeBinary(binary: String, trailingZeroesAllowed: Boolean = true): Packet {
    var remaining = binary

    val version = decodeNumber(remaining.substring(0 until 2))
    remaining = remaining.substring(2)

    val typeId = decodeNumber(remaining.substring(0 until 2))
    remaining = remaining.substring(2)

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
            OperatorPacket(version, emptyList()) // TODO
        }
    }

    if (remaining.isNotEmpty() && (!trailingZeroesAllowed || remaining.any { it != '0' })) {
        throw IllegalStateException("Unexpected remaining values found: $remaining")
    }

    return result
}


private fun decodeNumber(binary: String) = binary.toInt(2)