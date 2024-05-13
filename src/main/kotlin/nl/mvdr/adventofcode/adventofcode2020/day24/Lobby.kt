package nl.mvdr.adventofcode.adventofcode2020.day24

data class Lobby(private val blackTiles: Set<Tile>) {
    constructor(lines: Sequence<String>) : this(findInitialTiles(lines))

    val blackTileCount get() = blackTiles.count()

    fun simulateDays(days: Int = 100): Lobby = when (days) {
        0 -> this
        else -> simulateDay().simulateDays(days - 1)
    }

    private fun simulateDay() = this // TODO implement!
}

private fun findInitialTiles(lines: Sequence<String>) = lines.map(::Path)
    .map(Path::move)
    .groupingBy { it }
    .eachCount()
    .filter { it.value % 2 == 1 }
    .keys
    .toSet()

