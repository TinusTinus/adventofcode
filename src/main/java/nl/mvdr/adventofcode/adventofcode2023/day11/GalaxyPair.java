package nl.mvdr.adventofcode.adventofcode2023.day11;

import nl.mvdr.adventofcode.point.LongPoint;

/**
 * A pair of galaxies.
 *
 * @author Martijn van de Rijdt
 */
record GalaxyPair(LongPoint galaxy0, LongPoint galaxy1) {
    /**
     * @return the distance between the two galaxies
     */
    long computeDistance() {
        return galaxy0.manhattanDistance(galaxy1);
    }
}
