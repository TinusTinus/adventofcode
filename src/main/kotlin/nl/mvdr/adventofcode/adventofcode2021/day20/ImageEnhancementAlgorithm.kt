package nl.mvdr.adventofcode.adventofcode2021.day20

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

    private fun enhance(image: Image): Image = image // TODO implement!
}
