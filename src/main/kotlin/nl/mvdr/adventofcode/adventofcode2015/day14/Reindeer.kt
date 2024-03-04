package nl.mvdr.adventofcode.adventofcode2015.day14

/**
 * Representation of a reindeer.
 * The reindeer's [speed] is in km/s.
 * The durations ([flightDuration] and [restDuration]) are in seconds.
 */
data class Reindeer(val name: String, val speed: Int, val flightDuration: Int, val restDuration: Int) {
    /**
     * Computes the distance traveled in kilometers, after the given [raceDuration] in seconds.
     */
    fun distanceAfter(raceDuration: Int): Int {
        val cycleDuration = flightDuration + restDuration
        val distancePerCycle = speed * flightDuration
        val completeCycles = raceDuration / cycleDuration
        return completeCycles * distancePerCycle + distancePerCycle // TODO adding the distancePerCycle is only correct if the reindeer ends up resting!
    }
}

/**
 * Parses the given [text] as the string representation of a [Reindeer].
 * For example: "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."
 */
fun parseReindeer(text: String): Reindeer {
    val (name, speed, flightDuration, restDuration) = text.split(" can fly ", " km/s for ", " seconds, but then must rest for ", " seconds.")
    return Reindeer(name, speed.toInt(), flightDuration.toInt(), restDuration.toInt())
}