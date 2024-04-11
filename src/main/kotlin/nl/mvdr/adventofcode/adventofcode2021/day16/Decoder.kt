package nl.mvdr.adventofcode.adventofcode2021.day16

fun decode(hexadecimal: String): Packet = decodeBinary(toBinary(hexadecimal))

/**
 * Converts the given hexadecimal string to binary format.
 */
fun toBinary(hexadecimal: String): String = hexadecimal.map { it.toString().toInt(16) }
    .map { it.toString(2) }
    .joinToString(separator = "") { it.padStart(4, '0') }

private fun decodeBinary(binary: String): Packet = LiteralValuePacket(3) // TODO actually implement