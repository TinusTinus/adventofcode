package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.Set;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;

/**
 * Terrain on a hiking trail.
 *
 * @author Martijn van de Rijdt
 */
enum Terrain {
    PATH('.', Set.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)),
    FOREST('#', Set.of()),
    SLOPE_UP('^', Set.of(Direction.UP)),
    SLOPE_RIGHT('>', Set.of(Direction.RIGHT)),
    SLOPE_DOWN('v', Set.of(Direction.DOWN)),
    SLOPE_LEFT('<', Set.of(Direction.LEFT));

    private final char representation;
    private final Set<Direction> exits;

    /**
     * Constructor.
     * 
     * @param char single-character representation of this type of terrain
     * @param exits possible directions in which to exit tiles of this type
     */
    Terrain(char representation, Set<Direction> exits) {
        this.representation = representation;
        this.exits = exits;
    }

    /**
     * Finds the terrain value with the given representation.
     * 
     * @param representation single-character representation of a terrain value
     * @return terrain
     */
    static Terrain of(char representation) {
        return Stream.of(values())
                .filter(terrain -> terrain.representation == representation)
                .reduce((terrain0, terrain1) -> {
                    throw new IllegalStateException(
                            "There should be no more than one matching terrain value for '" + representation
                                    + "', but found: " + terrain0 + " and " + terrain1);
                }).orElseThrow(() -> new IllegalArgumentException("Invalid representation: " + representation));
    }
    
    /**
     * @return whether it is possible to walk on this type of terrain
     */
    boolean canEnter() {
        return this != FOREST;
    }
    
    /**
     * Checks whether it is possible to exit a tile of this type in the given direction.
     * 
     * @param direction direction
     * @return whether it is possible to travel in this direction from this terrain
     */
    boolean canExit(Direction direction) {
        return exits.contains(direction);
    }
}
