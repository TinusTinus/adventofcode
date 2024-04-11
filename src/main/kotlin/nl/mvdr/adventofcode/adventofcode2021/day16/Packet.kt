package nl.mvdr.adventofcode.adventofcode2021.day16

interface Packet {
    val version: Int
    val subPackets: List<Packet>

    fun totalVersionNumber(): Int = version + subPackets.sumOf(Packet::totalVersionNumber)
}