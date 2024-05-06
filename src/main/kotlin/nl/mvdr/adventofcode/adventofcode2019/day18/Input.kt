package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point

/**
 * Representation of the puzzle input.
 */
data class Input(val vault: Vault, val initialState: State)

/**
 * Parses the [lines] from the input file.
 */
fun parseInput(lines: List<String>): Input {
    var startingPoint: Point? = null
    val openPassages = mutableSetOf<Point>()
    val doors = mutableMapOf<Point, Door>()
    val keys = mutableMapOf<Key, Point>()
    for (y in lines.indices) {
        val line = lines[y]
        for (x in line.indices) {
            val point = Point(x, y)
            val c = line[x]
            if (c == '@') {
                startingPoint = point
            } else if (c == '.') {
                openPassages.add(point)
            } else if (Character.isLowerCase(c)) {
                keys[Key(c)] = point
            } else if (Character.isUpperCase(c)) {
                doors[point] = Door(c)
            } else require(c == '#') { "Unexpected input: $c" }
        }
    }

    val vault = Vault(openPassages, doors, keys)
    val initialState = State(startingPoint!!)
    return Input(vault, initialState)
}
