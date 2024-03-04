package nl.mvdr.adventofcode.adventofcode2015.day14

import kotlin.math.min

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
        // "cycle": the reindeer flies at top speed for as long as possible, then rests until it is ready to start flying again.
        val cycleDuration = flightDuration + restDuration

        val completeCycles = raceDuration / cycleDuration
        val distancePerCycle = speed * flightDuration
        val completeCycleDistance = completeCycles * distancePerCycle

        val remainingTime = raceDuration % cycleDuration
        val remainingFlightTime = min(remainingTime, flightDuration)

        return completeCycleDistance + remainingFlightTime * speed
    }
}

private const val RACE_DURATION = 2503

/**
 * Performs a race for the reindeer represented by the [lines] from the puzzle input.
 * The winning reindeer is determined by the greatest distance traveled.
 * This function returns the distance traveled, in km, by the winning reindeer.
 */
fun raceForDistance(lines: List<String>): Int = raceForDistance(lines.map(::parseReindeer), RACE_DURATION)

/**
 * Performs a race for the given [reindeer], for the given [raceDuration].
 * The winning reindeer is determined by the greatest distance traveled.
 * This function returns the distance traveled, in km, by the winning reindeer.
 */
fun raceForDistance(reindeer: List<Reindeer>, raceDuration: Int) = reindeer.maxOf { it.distanceAfter(raceDuration) }

/**
 * Performs a race for the reindeer represented by the [lines] from the puzzle input.
 * The winning reindeer is determined by points, which are given out for being in the lead at the end of each second.
 * This function returns the number of points accumulated by the winning reindeer.
 */
fun raceForPoints(lines: List<String>): Int = raceForPoints(lines.map(::parseReindeer), RACE_DURATION)

/**
 * Performs a race for the given [reindeer], for the given [raceDuration].
 * The winning reindeer is determined by points, which are given out for being in the lead at the end of each second.
 * This function returns the number of points accumulated by the winning reindeer.
 */
fun raceForPoints(reindeer: List<Reindeer>, raceDuration: Int): Int {
    val points = reindeer.associateWith { 0 }.toMutableMap()

    for (timestamp: Int in 1 until raceDuration) {
        for (reindeerInTheLead in inTheLeadAfter(reindeer, timestamp)) {
            points[reindeerInTheLead] = points[reindeerInTheLead]!! + 1
        }
    }
    return points.values.max()
}

/**
 * Determines which of the given [reindeer] is in the lead after the given [raceDuration] in seconds.
 * Returns a set containing at least one reindeer (multiple if they tie for the leading position).
 */
private fun inTheLeadAfter(reindeer: List<Reindeer>, raceDuration: Int): Set<Reindeer> {
    val distances = reindeer.associateWith { it.distanceAfter(raceDuration) }
    val maxDistance = distances.values.max()
    return distances.filterValues { it == maxDistance }.keys
}

/**
 * Parses the given [text] as the string representation of a [Reindeer].
 * For example: "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."
 */
private fun parseReindeer(text: String): Reindeer {
    val (name, speed, flightDuration, restDuration) = text.split(" can fly ", " km/s for ", " seconds, but then must rest for ", " seconds.")
    return Reindeer(name, speed.toInt(), flightDuration.toInt(), restDuration.toInt())
}