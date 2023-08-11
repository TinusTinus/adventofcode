package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.Set;

import nl.mvdr.adventofcode.point.Point3D;

/**
 * Representation of the state of a pocket dimension in three-dimensional space.
 *
 * @author Martijn van de Rijdt
 */
record PocketDimension3D(Set<Point3D> activeCubes) implements PocketDimension<Point3D> {
    
    @Override
    public PocketDimension3D create(Set<Point3D> newActiveCubes) {
        return new PocketDimension3D(newActiveCubes);
    }
    
    @Override
    public Set<Point3D> neighbours(Point3D point) {
        return point.neighboursInludingDiagonals();
    }
}
