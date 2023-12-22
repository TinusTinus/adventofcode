package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Axis3D;
import nl.mvdr.adventofcode.point.Orientation;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Representation of a single three-dimensional brick.
 *
 * A brick is made up out of a list of cubes.
 * These cubes are sorted by increasing coordinate value,
 * which means that the first cube is guaranteed to be closest to the ground,
 * regardless of the orientation of the brick.
 *
 * @param cubes the cubes making up this brick
 * @param orientation the orientation of this brick
 * @author Martijn van de Rijdt
 */
public record Brick(List<Point3D> cubes, Orientation orientation) {
    
    /**
     * Parses a brick.
     * 
     * @param text textual representation of a brick, for example: "1,0,1~1,2,1"
     * @return the brick
     */
    static Brick parse(String text) {
        var ends = Stream.of(text.split("~"))
                .map(Point3D::parse)
                .sorted() // sort by increasing coordinate values
                .toList();
        if (ends.size() != 2) {
            throw new IllegalArgumentException("Unable to parse as brick: " + text);
        }
        var firstCube = ends.getFirst();
        var lastCube = ends.getLast();
        
        // The two ends should differ on at most one axis.
        // Figure out which one.
        var axis = Stream.of(Axis3D.values())
                .filter(a -> firstCube.getValue(a) != lastCube.getValue(a))
                .reduce((axis0, axis1) -> {
                    throw new IllegalArgumentException("Values of " + firstCube + " and " + lastCube
                            + "differ in multiple axes, expected only one: " + axis0 + " and " + axis1);
                })
                .orElse(/* brick consists of a single cube; pick any axis */ Axis3D.X);

        var cubes = IntStream.range(firstCube.getValue(axis), lastCube.getValue(axis) + 1)
                    .mapToObj(value -> firstCube.withValue(axis, value))
                    .toList();
        return new Brick(cubes, axis.getOrientation());
    }
    
    /**
     * Lets the given bricks fall to the ground and settle.
     * 
     * @param bricks bricks
     * @return end result
     */
    static Set<Brick> settle(Set<Brick> bricks) {
        var result = bricks;
        var done = false;
        while (!done) {
            var newResult = fall(result);
            done = newResult.equals(result);
            result = newResult;
        }
        return result;
    }

    /**
     * Drops each of the given bricks by one unit if there is space to do so.
     * 
     * @param bricks set of bricks
     * @return updated state
     */
    private static Set<Brick> fall(Set<Brick> bricks) {
        return bricks.stream()
                .map(brick -> brick.fallIfPossible(bricks))
                .collect(Collectors.toSet());
    }
    
    /**
     * Drops this brick by one unit if it is not resting on anything.
     * 
     * @param bricks bricks
     * @return updated brick
     */
    private Brick fallIfPossible(Set<Brick> bricks) {
        Brick result;
        if (canFall(bricks)) {
            result = fall();
        } else {
            result = this;
        }
        return result;
    }
    
    /**
     * Checks whether this brick can fall, that is, whether it is unsupported by the ground or any other bricks.
     * 
     * @param bricks bricks
     * @return whether this brick can fall
     */
    boolean canFall(Set<Brick> bricks) {
        return !isOnTheGround() && supportingBricks(bricks).findAny().isEmpty();
    }
    
    /**
     * Drops the brick by one unit.
     * 
     * @return updated brick
     */
    private Brick fall() {
        var newCubes = cubes.stream()
                .map(brick -> Axis3D.Z.move(brick, -1))
                .collect(Collectors.toList());
        return new Brick(newCubes, orientation);
    }
    
    /**
     * Checks which of the given bricks is supporting this one.
     * 
     * @param bricks bricks
     * @return all other bricks which are supporting this one
     */
    Set<Brick> supportingBrickSet(Set<Brick> bricks) {
        return supportingBricks(bricks).collect(Collectors.toSet());
    }
    
    /**
     * Checks which of the given bricks is supporting this one.
     * 
     * @param bricks bricks
     * @return all other bricks which are supporting this one
     */
    private Stream<Brick> supportingBricks(Set<Brick> bricks) {
        return bricks.stream()
                .filter(brick -> brick.supports(this));
    }
    
    /**
     * Checks whether this brick supports the given other brick.
     * 
     * @param other other brick
     * @return whether this brick supports the given other brick
     */
    private boolean supports(Brick other) {
        // A brick cannot support itself
        var result = this != other;

        // Extra check to prevent many comparisons: can only support bricks where the lowest cube is one higher than this one
        result = result && this.cubes.getLast().z() == other.cubes.getFirst().z() - 1;
        
        if (other.orientation() == Orientation.VERTICAL) {
            result = result && supports(other.cubes().getFirst());
        } else {
            result = result && other.cubes()
                    .stream()
                    .anyMatch(this::supports);
        }
        return result;
    }
    
    /**
     * Checks whether this brick supports the given cube of another brick.
     * 
     * @param cubeOfOtherBrick cube of another brick
     * @return whether the given cube is resting on this brick
     */
    private boolean supports(Point3D cubeOfOtherBrick) {
        var cubeBelow = Axis3D.Z.move(cubeOfOtherBrick, -1);
        
        boolean result;
        if (orientation == Orientation.VERTICAL) {
            result = cubes.getLast().equals(cubeBelow);
        } else {
            result = cubes.contains(cubeBelow);
        }
        
        return result;
    }
    
    /**
     * @return whether this brick is resting on the ground
     */
    boolean isOnTheGround() {
        // Note that the first cube is guaranteed to be the one closest to the ground
        return cubes.getFirst().z() == 1;
    }
    
    @Override
    public String toString() {
        var result = cubes.getFirst() + "~" + cubes.getLast();
        result = result.replaceAll("<", "");
        result = result.replaceAll(">", "");
        return result;
    }
}
