package nl.mvdr.adventofcode.adventofcode2019.day12;

import nl.mvdr.adventofcode.point.Point3D;

/**
 * Representation of a moon.
 *
 * @author Martijn van de Rijdt
 */
class Moon {

    private final Point3D location;
    private final Point3D velocity;
    
    /**
     * Parses a line from the puzzle input into a moon in its initial position.
     * 
     * @param text input text
     * @return moon
     */
    static Moon parse(String text) {
        String input = text.replace("<x=", "<")
                .replace(", y=", ",")
                .replace(", z=", ",");
        Point3D location = Point3D.parse(input);
        
        return new Moon(location, new Point3D(0, 0, 0));
    }
    
    /**
     * Helper method to compute energy based on the given point.
     * 
     * @param point point in three dimensions
     * @return energy
     */
    private static int computeEnergy(Point3D point) {
        return Math.abs(point.getX()) + Math.abs(point.getY()) + Math.abs(point.getZ());
    }
    
    /**
     * Constructor.
     * 
     * @param location
     * @param velocity
     */
    private Moon(Point3D location, Point3D velocity) {
        super();
        this.location = location;
        this.velocity = velocity;
    }

    /** @return potential energy for this moon */
    private int computePotentialEnergy() {
        return computeEnergy(location);
    }
    
    /** @return kinetic energy for this moon */
    private int computeKineticEnergy() {
        return computeEnergy(velocity);
    }
    
    /** @return total energy for this moon */
    int computeTotalEnergy() {
        return computePotentialEnergy() + computeKineticEnergy();
    }

    @Override
    public String toString() {
        return "pos=" + location + ", vel=" + velocity + "]";
    }
}
