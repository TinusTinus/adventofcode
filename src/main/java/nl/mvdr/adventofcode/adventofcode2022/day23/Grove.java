package nl.mvdr.adventofcode.adventofcode2022.day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * State of the grove where elves need to plant seedlings.
 *
 * @param elves current locations of the elves
 * @param directions the order in which directions are to be considered in the next round
 * @param rounds the number of rounds which have been performed so far
 * @author Martijn van de Rijdt
 */
record Grove(Set<Point> elves, List<Direction> directions, int rounds) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Grove.class);

    private static final char ELF = '#';
    private static final char EMPTY_GROUND = '.';

    /**
     * Parses the puzzle input into the initial state of the grove.
     * 
     * @param lines puzzle input, containing a textual representation of the grove
     * @return grove
     */
    static Grove parse(List<String> lines) {
        Set<Point> elves = new HashSet<>();
        for (int y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                char c = line.charAt(x);
                if (c == ELF) {
                    // Elf found
                    elves.add(new Point(x, y));
                } else if (c != EMPTY_GROUND) {
                    throw new IllegalArgumentException("Unexpected input: " + c);
                }
            }
        }
        var directions = List.of(
                Direction.UP,     // North
                Direction.DOWN,   // South
                Direction.LEFT,   // West
                Direction.RIGHT); // East
        return new Grove(elves, directions, 0);
    }

    /**
     * Performs the simulation for this grove for ten rounds.
     * 
     * @return state of the grove
     */
    Grove simulateTenRounds() {
        Grove grove = this;
        for (var round = 0; round != 10; round++) {
            grove = grove.performRound();
            LOGGER.debug("After {} rounds: {}", Integer.valueOf(round), grove);
        }
        return grove;
    }
    
    /**
     * Performs the simulation for this grove.
     * 
     * @return number of expired rounds when elves no longer move
     */
    int simulateUntilComplete() {
        Grove current = this;
        Grove next = current.performRound();
        while (!current.elves().equals(next.elves())) {
            current = next;
            next = current.performRound();
            LOGGER.debug("After {} rounds: {}", Integer.valueOf(next.rounds()), next);
        }
        return next.rounds;
    }
    
    /**
     * Performs a single round.
     * 
     * @return result of performing a single round
     */
    private Grove performRound() {
        return move(propose());
    }
    
    /**
     * Lets each elf make a proposal for their next move.
     * 
     * In the resulting maps, the key is an elf's current location and the value is the elf's proposed new location.
     * 
     * @return proposals
     */
    private Map<Point, Point> propose() {
        Map<Point, Point> result = new HashMap<>();
        for (Point elf : elves) {
            propose(elf).ifPresent(newPosition -> result.put(elf, newPosition));
        }
        return result;
    }
    
    /**
     * Makes a proposal for a single elf.
     * 
     * @param elf current position of the elf
     * @return proposal
     */
    private Optional<Point> propose(Point elf) {
        Optional<Point> result = Optional.empty();
        if (hasNeighbour(elf)) {
            var i = 0;
            while (result.isEmpty() && i != directions.size()) {
                var direction = directions.get(i);
                if (canMove(elf, direction)) {
                    result = Optional.of(direction.move(elf));
                }
                i++;
            }
        }
        return result;
    }
    
    /**
     * Determines whether there is an elf in one of the locations neighbouring the given elf's location.
     * 
     * If there is no Elf in the N, NE, or NW adjacent positions, the Elf could propose moving North one step.
     * 
     * @param elf elf's current location
     * @return whether moving North is an option
     */
    private boolean hasNeighbour(Point elf) {
        return elves.contains(elf.northNeighbour())
                || elves.contains(elf.eastNeighbour())
                || elves.contains(elf.southNeighbour())
                || elves.contains(elf.westNeighbour())
                || elves.contains(elf.northwestNeighbour())
                || elves.contains(elf.northeastNeighbour())
                || elves.contains(elf.southeastNeighbour())
                || elves.contains(elf.southwestNeighbour());
    }

    /**
     * Determines whether the given elf could move to the given direction.
     * 
     * @param elf elf's current location
     * @return whether moving in the given direction is an option
     */
    private boolean canMove(Point elf, Direction direction) {
        return switch(direction) {
            case UP    -> canMoveNorth(elf);
            case DOWN  -> canMoveSouth(elf);
            case LEFT  -> canMoveWest(elf);
            case RIGHT -> canMoveEast(elf);
            default -> throw new IllegalArgumentException("Unexpected direction: " + direction);
        };
    }
    
    /**
     * Determines whether the given elf could move to the North.
     * 
     * If there is no Elf in the N, NE, or NW adjacent positions, the Elf could propose moving North one step.
     * 
     * @param elf elf's current location
     * @return whether moving North is an option
     */
    private boolean canMoveNorth(Point elf) {
        return !elves.contains(elf.northNeighbour())
                && !elves.contains(elf.northwestNeighbour())
                && !elves.contains(elf.northeastNeighbour());
    }
    
    /**
     * Determines whether the given elf could move to the South.
     * 
     * If there is no Elf in the S, SE, or SW adjacent positions, the Elf proposes moving South one step.
     * 
     * @param elf elf's current location
     * @return whether moving South is an option
     */
    private boolean canMoveSouth(Point elf) {
        return !elves.contains(elf.southNeighbour())
                && !elves.contains(elf.southwestNeighbour())
                && !elves.contains(elf.southeastNeighbour());
    }
    
    /**
     * Determines whether the given elf could move to the West.
     * 
     * If there is no Elf in the W, NW, or SW adjacent positions, the Elf proposes moving west one step.
     * 
     * @param elf elf's current location
     * @return whether moving West is an option
     */
    private boolean canMoveWest(Point elf) {
        return !elves.contains(elf.westNeighbour())
                && !elves.contains(elf.northwestNeighbour())
                && !elves.contains(elf.southwestNeighbour());
    }
    
    /**
     * Determines whether the given elf could move to the East.
     * 
     * If there is no Elf in the E, NE, or SE adjacent positions, the Elf proposes moving east one step.
     * 
     * @param elf elf's current location
     * @return whether moving East is an option
     */
    private boolean canMoveEast(Point elf) {
        return !elves.contains(elf.eastNeighbour())
                && !elves.contains(elf.northeastNeighbour())
                && !elves.contains(elf.southeastNeighbour());
    }
    
    /**
     * Performs the given proposals where possible.
     * 
     * @param proposals moves proposed by the elves
     * @return updated state of the grove
     */
    private Grove move(Map<Point, Point> proposals) {
        Set<Point> newElves = new HashSet<>();
        for (Point elf : elves) {
            Point proposal = proposals.get(elf);
            if (Collections.frequency(proposals.values(), proposal) == 1) {
                // Move to the new location
                newElves.add(proposal);
            } else {
                // No proposal, or the proposal was done by multiple elves.
                // This elf stays where they were.
                newElves.add(elf);
            }
        }
        List<Direction> newDirections = new ArrayList<>();
        newDirections.addAll(directions.subList(1, directions.size()));
        newDirections.add(directions.get(0));
        
        return new Grove(newElves, newDirections, rounds + 1);
    }
    
    /**
     * @return the number of empty ground tiles contained by the smallest rectangle that contains every Elf
     */
    int countEmptyTiles() {
        var minX = Point.minX(elves);
        var maxX = Point.maxX(elves);
        var minY = Point.minY(elves);
        var maxY = Point.maxY(elves);
        
        var totalTiles = (maxX + 1 - minX) * (maxY + 1 - minY);
        
        return totalTiles - elves.size();
    }
    
    @Override
    public String toString() {
        var minX = Point.minX(elves);
        var maxX = Point.maxX(elves);
        var minY = Point.minY(elves);
        var maxY = Point.maxY(elves);
        
        StringBuilder builder = new StringBuilder();
        builder.append("Grove:");
        for (var y = minY; y != maxY + 1; y++) {
            builder.append("\n");
            for (var x = minX; x != maxX + 1; x++) {
                if (elves.contains(new Point(x, y))) {
                    builder.append(ELF);
                } else {
                    builder.append(EMPTY_GROUND);
                }
            }
        }
        return builder.toString();
    }
}
