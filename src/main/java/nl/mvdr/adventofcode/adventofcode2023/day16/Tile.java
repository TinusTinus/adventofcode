package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;

/**
 * Contents of a single tile in the contraption.
 *
 * @author Martijn van de Rijdt
 */
enum Tile {
    EMPTY('.') {
        @Override
        Set<Direction> newDirections(Direction direction) {
            // Keep moving in the same direction.
            return Set.of(direction);
        }
    },
    FORWARD_MIRROR('/') {
        @Override
        Set<Direction> newDirections(Direction direction) {
            var newDirection = switch(direction) {
                case RIGHT -> Direction.UP;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.DOWN;
                case UP -> Direction.RIGHT;
                case UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT -> throw new IllegalArgumentException("Diagonal directions are not supported: " + direction);
                default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
            };
            return Set.of(newDirection);
        }
    },
    BACK_MIRROR('\\') {
        @Override
        Set<Direction> newDirections(Direction direction) {
            var newDirection = switch(direction) {
                case RIGHT -> Direction.DOWN;
                case DOWN -> Direction.RIGHT;
                case LEFT -> Direction.UP;
                case UP -> Direction.LEFT;
                case UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT -> throw new IllegalArgumentException("Diagonal directions are not supported: " + direction);
                default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
            };
            return Set.of(newDirection);
        }
    },
    HORIZONTAL_SPLITTER('-') {
        @Override
        Set<Direction> newDirections(Direction direction) {
            return switch(direction) {
                case LEFT, RIGHT -> Set.of(direction);
                case UP, DOWN -> Set.of(Direction.LEFT, Direction.RIGHT);
                case UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT -> throw new IllegalArgumentException("Diagonal directions are not supported: " + direction);
                default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
            };
        }
    },
    VERTICAL_SPLITTER('|') {
        @Override
        Set<Direction> newDirections(Direction direction) {
            return switch(direction) {
            case UP, DOWN-> Set.of(direction);
            case LEFT, RIGHT -> Set.of(Direction.UP, Direction.DOWN);
            case UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT -> throw new IllegalArgumentException("Diagonal directions are not supported: " + direction);
            default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
        };

        }
    }
    ;
    
    private final char representation;
    
    /**
     * Finds the tile with the given representation.
     * 
     * @param representation character representing a single tile, such as '.' or '/'
     * @return tile
     */
    static Tile of(char representation) {
        return Stream.of(values())
                .filter(value -> value.representation == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid tile: " + representation));
    }
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of this type of tile
     */
    Tile(char representation) {
        this.representation = representation;
    }
    
    /**
     * Determines the next location of the head of the given beam, if it passes through this tile.
     * 
     * @param beamHead current location of the beam, where the current location corresponds to this tile
     * @return next location(s) of the beam, can contain one or two elements (two in case of a splitter)
     */
    Set<BeamHead> passThrough(BeamHead beamHead) {
        return newDirections(beamHead.direction())
                .stream()
                .map(direction -> new BeamHead(direction.move(beamHead.location()), direction))
                .collect(Collectors.toSet());
    }
    
    /**
     * Determines the next direction of a beam, if it enters this tile in the given direction.
     * 
     * @param direction direction of the beam when entering this tile
     * @return next direction(s) of the beam, can contain one or two elements (two in case of a splitter)
     */
    abstract Set<Direction> newDirections(Direction direction);
}
