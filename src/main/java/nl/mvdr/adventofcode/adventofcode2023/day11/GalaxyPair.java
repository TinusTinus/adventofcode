package nl.mvdr.adventofcode.adventofcode2023.day11;

import nl.mvdr.adventofcode.point.Point;

/**
 * A pair of galaxies.
 *
 * @author Martijn van de Rijdt
 */
record GalaxyPair(Point galaxy0, Point galaxy1) {
    /**
     * @return the distance between the two galaxies
     */
    int computeDistance() {
        return galaxy0.manhattanDistance(galaxy1);
    }
}
