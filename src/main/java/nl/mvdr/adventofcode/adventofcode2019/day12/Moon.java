package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.processing.Generated;

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
        for (Moon moon : system) {
            newVelocities.put(moon, moon.getVelocity());
        }
        
        for (Moon moon0 : system) {
            for (Moon moon1 : system) {
                if (moon0.getLocation().getX() < moon1.getLocation().getX()) {
                    newVelocities.merge(moon0, new Point3D(1, 0, 0), Point3D::add);
                    newVelocities.merge(moon1, new Point3D(-1, 0, 0), Point3D::add);
                } else if (moon0.getLocation().getX() < moon1.getLocation().getX()) {
                    newVelocities.merge(moon0, new Point3D(-1, 0, 0), Point3D::add);
                    newVelocities.merge(moon1, new Point3D(1, 0, 0), Point3D::add);
                }
                
                if (moon0.getLocation().getY() < moon1.getLocation().getY()) {
                    newVelocities.merge(moon0, new Point3D(0, 1, 0), Point3D::add);
                    newVelocities.merge(moon1, new Point3D(0, -1, 0), Point3D::add);
                } else if (moon0.getLocation().getY() < moon1.getLocation().getY()) {
                    newVelocities.merge(moon0, new Point3D(0, -1, 0), Point3D::add);
                    newVelocities.merge(moon1, new Point3D(0, 1, 0), Point3D::add);
                }
                
                if (moon0.getLocation().getZ() < moon1.getLocation().getZ()) {
                    newVelocities.merge(moon0, new Point3D(0, 0, 1), Point3D::add);
                    newVelocities.merge(moon1, new Point3D(0, 0, -1), Point3D::add);
                } else if (moon0.getLocation().getZ() < moon1.getLocation().getZ()) {
                    newVelocities.merge(moon0, new Point3D(0, 0, -1), Point3D::add);
                    newVelocities.merge(moon1, new Point3D(0, 0, 1), Point3D::add);
                }
            }
        }
        
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
