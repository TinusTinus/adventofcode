package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.List;
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
                .findFirst()
                .orElse(/* brick consists of a single cube; pick any axis */ Axis3D.X);

        var cubes = IntStream.range(firstCube.getValue(axis), lastCube.getValue(axis) + 1)
                    .mapToObj(value -> firstCube.withValue(axis, value))
                    .toList();
        return new Brick(cubes, axis.getOrientation());
    }
    
    /**
     * @return whether this cube is resting on the ground
     */
    private boolean isOnTheGround() {
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
