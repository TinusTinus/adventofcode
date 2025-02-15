package nl.mvdr.adventofcode.adventofcode2023.day11;

/**
 * A pair of galaxies.
 *
 * @author Martijn van de Rijdt
 */
record GalaxyPair(Galaxy galaxy0, Galaxy galaxy1) {
    /**
     * @return the distance between the two galaxies
     */
    long computeDistance() {
        return Math.abs(galaxy0.x() - galaxy1.x()) + Math.abs(galaxy0.y() - galaxy1.y());
    }
}
