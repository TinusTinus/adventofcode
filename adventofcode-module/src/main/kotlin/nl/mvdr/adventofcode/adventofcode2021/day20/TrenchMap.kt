package nl.mvdr.adventofcode.adventofcode2021.day20

/**
 * Solves the puzzle, given the [lines] from the puzzle input and the number of [times] to enhance the image.
 */
fun solve(lines: Sequence<String>, times: Int): Int {
    val (algorithm, image) = parse(lines.toList())
    val enhanced = algorithm.enhance(image, times)
    return enhanced.countLightPixels()
}

private fun parse(lines: List<String>): Pair<ImageEnhancementAlgorithm, Image> {
    val algorithm = ImageEnhancementAlgorithm(lines.first())
    if (lines[1].isNotEmpty()) {
        throw IllegalArgumentException("Second line is expected to be empty, but was: " + lines[1])
    }
    val image = Image(lines.drop(2))
    return Pair(algorithm, image)
}