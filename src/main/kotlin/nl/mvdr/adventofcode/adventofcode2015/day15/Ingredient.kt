package nl.mvdr.adventofcode.adventofcode2015.day15

data class Ingredient(val name: String, val properties: Map<String, Int>) {
}

/**
 * Parses the given [text] as the string representation of an ingredient.
 * For example:
 * "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8"
 */
fun parseIngredient(text: String): Ingredient {
        val (name, propertiesString) = text.split(": ")
        val propertyStrings = propertiesString.split(", ")
        val properties = mutableMapOf<String, Int>()
        propertyStrings.forEach {
                val (propertyName, propertyValue) = it.split(" ")
                properties[propertyName] = propertyValue.toInt()
        }
        return Ingredient(name, properties)
}