package nl.mvdr.adventofcode.adventofcode2021.day16

data class LiteralValuePacket(override val version: Int, private val value: Long) : Packet {
    override val subPackets: List<Packet> get() = emptyList()
}
