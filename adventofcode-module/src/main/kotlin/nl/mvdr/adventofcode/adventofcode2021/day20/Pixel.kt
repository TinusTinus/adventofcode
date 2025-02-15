package nl.mvdr.adventofcode.adventofcode2021.day20

enum class Pixel(val representation: Char, val value: Int) {
    LIGHT('#', 1),
    DARK('.', 0)
}

fun parsePixel(representation: Char) = Pixel.entries.first { it.representation == representation }
