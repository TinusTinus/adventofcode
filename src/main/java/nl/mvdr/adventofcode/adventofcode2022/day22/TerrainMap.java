package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Map;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the map as presented in the puzzle input.
 *
 * @param map map of terrain, as provided in the puzzle input
 * @param squares six squares, making up the entire map
 * @author Martijn van de Rijdt
 */
record TerrainMap(Map<Point, Terrain> map, Set<Square> squares) {
    
}
