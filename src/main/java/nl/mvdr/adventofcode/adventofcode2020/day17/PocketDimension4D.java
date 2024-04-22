package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.Set;

import nl.mvdr.adventofcode.point.Point4D;

/**
 * Representation of the state of a pocket dimension in four-dimensional space.
 *
 * @author Martijn van de Rijdt
 */
record PocketDimension4D(Set<Point4D> activeCubes) implements PocketDimension<Point4D> {
    
    @Override
    public PocketDimension4D create(Set<Point4D> newActiveCubes) {
        return new PocketDimension4D(newActiveCubes);
    }
    
    @Override
    public Set<Point4D> neighbours(Point4D point) {
        return point.neighbours();
    }
}
