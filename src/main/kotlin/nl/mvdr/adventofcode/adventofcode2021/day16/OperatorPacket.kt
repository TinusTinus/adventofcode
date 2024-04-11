package nl.mvdr.adventofcode.adventofcode2021.day16

data class OperatorPacket(override val version: Int, override val subPackets: List<Packet>, private val packetType: PacketType) : Packet {
    override fun evaluate() = when (packetType) {
        PacketType.SUM -> subPackets.sumOf(Packet::evaluate)
        PacketType.PRODUCT -> subPackets.map(Packet::evaluate).reduce(Long::times)
        PacketType.MINIMUM -> subPackets.minOf(Packet::evaluate)
        PacketType.MAXIMUM -> subPackets.maxOf(Packet::evaluate)
        PacketType.GREATER_THAN -> toLong(subPackets.first().evaluate() > subPackets.last().evaluate())
        PacketType.LESS_THAN -> toLong(subPackets.first().evaluate() < subPackets.last().evaluate())
        PacketType.EQUAL_TO -> toLong(subPackets.first().evaluate() == subPackets.last().evaluate())
        else -> throw IllegalStateException("Unsupported packet type: $packetType")
    }
}

private fun toLong(value: Boolean) = when(value) {
    true -> 1L
    false -> 0L
}
