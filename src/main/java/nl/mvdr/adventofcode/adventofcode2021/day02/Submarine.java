package nl.mvdr.adventofcode.adventofcode2021.day02;

import nl.mvdr.adventofcode.point.Point;

/**
 * State of the submarine.
 *
 * @param location, where the x coordinate is the horizontal position and the y coordinate is depth
 * @param aim the aim value (only relevant for part 2 of the puzzle)
 * @author Martijn van de Rijdt
 */
record Submarine(Point location, int aim) {
    static final Submarine STARTING_POSITION = new Submarine(Point.ORIGIN, 0);
}
