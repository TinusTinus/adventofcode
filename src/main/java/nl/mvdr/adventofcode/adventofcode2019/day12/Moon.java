package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.point.Axis3D;
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
     * Performs a single simulation step.
     * 
     * @param system system
     * @return updated system
     */
    static List<Moon> performSimulationStep(List<Moon> system) {
        Map<Moon, Point3D> newVelocities = new HashMap<>();
        system.forEach(moon -> newVelocities.put(moon, moon.getVelocity()));
        
        system.forEach(moon0 -> 
                system.forEach(moon1 -> newVelocities.merge(moon1,
                            moon0.getLocation().subtract(moon1.getLocation()).signum(),
                            Point3D::add)));
        
        return system.stream()
                .map(moon -> moon.updateVelocity(newVelocities.get(moon)))
                .collect(Collectors.toList());
    }

    
    /**
     * Helper method to compute energy based on the given point.
     * 
     * @param point point in three dimensions
     * @return energy
     */
    private static int computeEnergy(Point3D point) {
        return Math.abs(point.x()) + Math.abs(point.y()) + Math.abs(point.z());
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

    /** @return total energy for this moon */
    int computeTotalEnergy() {
        return computePotentialEnergy() * computeKineticEnergy();
    }
    
    /** @return potential energy for this moon */
    private int computePotentialEnergy() {
        return computeEnergy(location);
    }
    
    /** @return kinetic energy for this moon */
    private int computeKineticEnergy() {
        return computeEnergy(velocity);
    }

    /**
     * Applies the given velocity to this moon.
     * 
     * @param newVelocity new velocity
     * @return updated moon
     */
    Moon updateVelocity(Point3D newVelocity) {
        Point3D newLocation = location.add(newVelocity);
        return new Moon(newLocation, newVelocity);
    }
    
    Point3D getLocation() {
        return location;
    }
    
    Point3D getVelocity() {
        return velocity;
    }
    
    /**
     * Determines whether this moon's location and velocity are equal to the given moon's in the given axis.
     * 
     * @param other moon to compare to
     * @param axis axis to compare along
     * @return whether location and velocity value are equal in the given axis
     */
    boolean equalsOnAxis(Moon other, Axis3D axis) {
        return this.location.getValue(axis) == other.location.getValue(axis)
                && this.velocity.getValue(axis) == other.velocity.getValue(axis);
    }

    @Override
    public String toString() {
        return "pos=" + location + ", vel=" + velocity + "]";
    }

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(location, velocity);
    }

    @Override
    @Generated("Eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Moon other = (Moon) obj;
        return Objects.equals(location, other.location) && Objects.equals(velocity, other.velocity);
    }
}
