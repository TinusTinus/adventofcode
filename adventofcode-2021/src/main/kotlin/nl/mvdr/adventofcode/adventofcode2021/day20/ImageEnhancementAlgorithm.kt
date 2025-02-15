package nl.mvdr.adventofcode.adventofcode2021.day20

import nl.mvdr.adventofcode.point.Point
import kotlin.streams.asSequence

data class ImageEnhancementAlgorithm(val pixels: List<Pixel>) {

    constructor(algorithmString: String) : this(algorithmString.map(::parsePixel))

    init {
        if (pixels.size != 512) {
            throw IllegalArgumentException("Algorithm must contain exactly 512 pixels, but was: " + pixels.size)
        }
    }

    fun enhance(image: Image, times: Int): Image = when(times) {
        0 -> image
        else -> enhance(enhance(image), times - 1)
    }

    private fun enhance(image: Image): Image {
        val imageMap = Point.points(image.minX() - 1, image.maxX() + 1, image.minY() - 1, image.maxY() + 1)
            .asSequence()
            .associateWith { enhance(image, it) }

        val defaultColour = enhance(image, Point(image.minX() - 2, image.minY() - 2))

        return Image(imageMap, defaultColour)
    }

    private fun enhance(image: Image, point: Point): Pixel {
        val index = listOf(point.aboveNeighbour().leftNeighbour(),
                point.aboveNeighbour(),
                point.aboveNeighbour().rightNeighbour(),
                point.leftNeighbour(),
                point,
                point.rightNeighbour(),
                point.belowNeighbour().leftNeighbour(),
                point.belowNeighbour(),
                point.belowNeighbour().rightNeighbour())
            .map { image.get(it) }
            .map(Pixel::value)
            .joinToString(separator = "")
            .toInt(2)
        return pixels[index]
    }
}
