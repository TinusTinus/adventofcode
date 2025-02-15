package nl.mvdr.adventofcode.adventofcode2015.day03

import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.point.Point

/**
 * Determines which houses will be visited by Santa if he follows the given directions.
 *
 * @param directions movement instructions
 * @return houses visited during the trip
 */
fun visitHouses(directions: List<Direction>): Set<Point> {
    var currentLocation = Point.ORIGIN
    val visited = mutableSetOf(currentLocation)
    for (direction in directions) {
        currentLocation = direction.move(currentLocation)
        visited.add(currentLocation)
    }
    return visited
}