package nl.mvdr.adventofcode.adventofcode2015.day14

/**
 * Representation of a reindeer.
 * The reindeer's [speed] is in km/s.
 * The durations ([flightDuration] and [restDuration]) are in seconds.
 */
data class Reindeer(val name: String, val speed: Int, val flightDuration: Int, val restDuration: Int) {
    fun distanceAfter(raceDuration: Int): Int = 3 // TODO implement!
}

/**
 * Parses the given [text] as the string representation of a [Reindeer].
 * For example: "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."
 */
fun parseReindeer(text: String): Reindeer {
    val (name, speed, flightDuration, restDuration) = text.split(" can fly ", " km/s for ", " seconds, but then must rest for ", " seconds.")
    return Reindeer(name, speed.toInt(), flightDuration.toInt(), restDuration.toInt())
}