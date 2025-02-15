package nl.mvdr.adventofcode.adventofcode2021.day20

import nl.mvdr.adventofcode.point.Point

/**
 * An infinite image.
 *
 * Every pixel value not explicitly specified in the given [image] map has the [defaultColour].
 */
data class Image(private val image: Map<Point, Pixel>, private val defaultColour: Pixel) {
    constructor(lines: List<String>) : this(Point.parse2DMap(lines, ::parsePixel), Pixel.DARK)

    fun get(point: Point) = image[point] ?: defaultColour

    /**
     * Returns the minimum x value where a non-default colour pixel may occur.
     */
    fun minX() = Point.minX(image.keys)

    /**
     * Returns the maximum x value where a non-default colour pixel may occur.
     */
    fun maxX() = Point.maxX(image.keys)

    /**
     * Returns the minimum y value where a non-default colour pixel may occur.
     */
    fun minY() = Point.minY(image.keys)

    /**
     * Returns the maximum y value where a non-default colour pixel may occur.
     */
    fun maxY() = Point.maxY(image.keys)

    fun countLightPixels() = when (defaultColour) {
        Pixel.DARK -> image.values.count { it == Pixel.LIGHT }
        Pixel.LIGHT -> throw IllegalStateException("The number of light pixels is infinite.")
    }
}
