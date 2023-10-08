package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Map;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a cube.
 *
 * @param map map of the terrain, as provided in the puzzle input
 * @param faces faces of the cube represented by the map
 * @author Martijn van de Rijdt
 */
record Cube(Map<Point, Terrain> map, Set<CubeFace> faces) {
    
}
