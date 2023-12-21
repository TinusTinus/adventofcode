package nl.mvdr.adventofcode.adventofcode2023.day21;

import nl.mvdr.adventofcode.point.Point;

/**
 * A record containing a starting point and a number of steps.
 * 
 * Intended to be used as the key in a cache.
 *
 * @author Martijn van de Rijdt
 */
record StartingPointAndSteps(Point startingPoint, int steps) {

}
