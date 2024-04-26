package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

data class Move(val amphipod: Amphipod, val target: Point) {
    private val distance: Int get() = amphipod.location.manhattanDistance(target)

    val energyCost: Int get() = distance * amphipod.type.energyPerStep
}
