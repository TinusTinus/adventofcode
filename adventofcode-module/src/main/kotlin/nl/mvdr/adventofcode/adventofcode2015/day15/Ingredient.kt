package nl.mvdr.adventofcode.adventofcode2015.day15

data class Ingredient(val name: String, val properties: Map<Property, Int>) {
    val calories: Int get() = properties[Property.CALORIES]!!
}

/**
 * Parses the given [text] as the string representation of an ingredient.
 * For example:
 * "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8"
 */
fun parseIngredient(text: String): Ingredient {
        val (name, propertiesString) = text.split(": ")
        val propertyStrings = propertiesString.split(", ")
        val properties = mutableMapOf<Property, Int>()
        propertyStrings.forEach {
                val (propertyName, propertyValue) = it.split(" ")
                val property = parseProperty(propertyName)
                properties[property] = propertyValue.toInt()
        }
        return Ingredient(name, properties)
}