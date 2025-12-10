package nl.mvdr.adventofcode.adventofcode2025.day10

data class Button(val lights: Set<Int>)

fun parseButton(text: String): Button {
    if (!text.startsWith("(") || !text.endsWith(")")) {
        throw IllegalArgumentException("Representation of a button must be enclosed in brackets. Given value: $text")
    }

    val lights = text.substring(1, text.length - 1)
        .split(",")
        .map(String::toInt)
        .toSet()

    return Button(lights)
}
