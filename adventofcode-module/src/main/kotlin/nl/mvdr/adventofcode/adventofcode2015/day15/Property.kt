package nl.mvdr.adventofcode.adventofcode2015.day15

/**
 * Property of an ingredient.
 */
enum class Property(val textRepresentation: String) {
    CAPACITY("capacity"),
    DURABILITY("durability"),
    FLAVOR("flavor"),
    TEXTURE("texture"),
    CALORIES("calories")
}

/**
 * Finds the property with the given [text] as its representation.
 */
fun parseProperty(text: String): Property = Property.entries.find { it.textRepresentation == text }!!
