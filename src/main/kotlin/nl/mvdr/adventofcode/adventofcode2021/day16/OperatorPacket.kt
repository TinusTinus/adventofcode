package nl.mvdr.adventofcode.adventofcode2021.day16

data class OperatorPacket(override val version: Int, override val subPackets: List<Packet>) : Packet
