package nl.mvdr.adventofcode.adventofcode2021.day23

object Burrow {
    val hallwaySpaces = setOf(
        HallwaySpace(1),
        HallwaySpace(2),
        HallwaySpace(4),
        HallwaySpace(6),
        HallwaySpace(8),
        HallwaySpace(10),
        HallwaySpace(11)
    )

    val roomSpaces = setOf(
        RoomSpace(3, 2, AmphipodType.AMBER),
        RoomSpace(3, 3, AmphipodType.AMBER),
        RoomSpace(5, 2, AmphipodType.BRONZE),
        RoomSpace(5, 3, AmphipodType.BRONZE),
        RoomSpace(7, 2, AmphipodType.COPPER),
        RoomSpace(7, 3, AmphipodType.COPPER),
        RoomSpace(9, 2, AmphipodType.DESERT),
        RoomSpace(9, 3, AmphipodType.DESERT),
    )
}