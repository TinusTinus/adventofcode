package nl.mvdr.adventofcode.adventofcode2021.day16

data class LiteralValuePacket(override val version: Int, val value: Int) : Packet {
    override val subPackets: List<Packet> get() = emptyList()
}
