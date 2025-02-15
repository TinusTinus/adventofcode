package nl.mvdr.adventofcode.adventofcode2015.day02

/**
 * Representation of a box, where all dimensions are in feet.
 */
data class Box(val length: Int, val width: Int, val height: Int) {
    /**
     * @return the number of square feet of wrapping paper required to wrap the box
     */
    fun computeWrappingPaper(): Int {
        val surfaceAreas = listOf(length * width, width * height, height * length)
        val smallestSurfaceArea = surfaceAreas.min()
        return surfaceAreas.sumOf { area -> area * 2 } + smallestSurfaceArea
    }

    private fun volume() = length * width * height

    /**
     * @return the number of feet of ribbon needed to wrap the box
     */
    fun computeRibbonLength(): Int {
        val smallestPerimeter = listOf(length, width, height).sorted()
            .subList(0, 2) // pick the smallest two values
            .sum() * 2
        return smallestPerimeter + volume()
    }
}

/**
 * Parses the string representation of a box.
 *
 * @param line textual representation of a box, for example: "2x3x4"
 * @return box
 */
fun parse(line: String): Box {
    val (length, width, height) = line.split("x")
            .map { part -> part.toInt() }
    return Box(length, width, height)
}
