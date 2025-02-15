package nl.mvdr.adventofcode.adventofcode2020.day24

import nl.mvdr.adventofcode.point.Point

data class Tile(val location: Point) {
    val neighbours get() = Direction.entries.map { direction -> direction.move(this) }.toSet()
}
