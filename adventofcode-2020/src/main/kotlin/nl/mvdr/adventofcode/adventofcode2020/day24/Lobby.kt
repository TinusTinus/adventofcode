package nl.mvdr.adventofcode.adventofcode2020.day24

data class Lobby(private val blackTiles: Set<Tile>) {
    constructor(lines: Sequence<String>) : this(findInitialTiles(lines))

    val blackTileCount get() = blackTiles.count()

    fun simulateDays(days: Int = 100): Lobby = when (days) {
        0 -> this
        else -> simulateDay().simulateDays(days - 1)
    }

    private fun simulateDay(): Lobby {
        val newBlackTiles = mutableSetOf<Tile>()

        for (blackTile in blackTiles) {
            // Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
            if (countBlackNeighbours(blackTile) in 1 ..2) {
                // Not flipped
                newBlackTiles.add(blackTile)
            }

            // Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
            blackTile.neighbours
                .filter(this::isWhite)
                .filter { countBlackNeighbours(it) == 2 }
                .forEach(newBlackTiles::add)
        }

        return Lobby(newBlackTiles)
    }

    private fun isBlack(tile: Tile) = blackTiles.contains(tile)

    private fun isWhite(tile: Tile) = !isBlack(tile)

    private fun countBlackNeighbours(tile: Tile) = tile.neighbours.count(this::isBlack)
}

private fun findInitialTiles(lines: Sequence<String>) = lines.map(::Path)
    .map(Path::move)
    .groupingBy { it }
    .eachCount()
    .filter { it.value % 2 == 1 }
    .keys
    .toSet()

