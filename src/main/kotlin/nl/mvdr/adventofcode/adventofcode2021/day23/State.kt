package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

data class State(val amphipods: Set<Amphipod>) {
    constructor(lines: Sequence<String>) : this(parseAmphipods(lines.toList()))

    /**
     * Determines possible next states, after moving a single amphipod.
     * Returns pairs of the next state and the corresponding energy cost.
     */
    fun nextStates(): Set<Pair<State, Int>> = setOf()
}

private fun parseAmphipods(lines: List<String>) = lines.indices.flatMap { y -> lines[y].indices.map { x -> Point(x, y) } }
    .filter { lines[it.y][it.x].isLetter() }
    .map { Amphipod(parseAmphipodType(lines[it.y][it.x]), it) }
    .toSet()
