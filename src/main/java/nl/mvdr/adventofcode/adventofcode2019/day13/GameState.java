package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * State of the arcade game.
 *
 * @author Martijn van de Rijdt
 */
class GameState {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GameState.class);
    
    private final Map<Point, Tile> tiles;
    private final List<Long> unprocessedOutputs;
    private final List<Long> inputs;
    private long score;

    /** Constructor. */
    GameState() {
        super();
        this.tiles = new HashMap<>();
        this.unprocessedOutputs = new ArrayList<>(2);
        this.inputs = new LinkedList<>();
    }
    
    /**
     * Processes an output value from the Intcode computer.
     * 
     * @param code output value; can be a coordinate, a representation of a
     *             {@link Tile} or a score value
     */
    void process(long code) {
        if (unprocessedOutputs.size() == 2) {
            int x = Math.toIntExact(unprocessedOutputs.get(0).longValue());
            int y = Math.toIntExact(unprocessedOutputs.get(1).longValue());
            if (x == -1 && y == 0) {
                score = code;
            } else {
                Point point = new Point(x, y);
                Tile tile = Tile.of(code);
                tiles.put(point, tile);
            }
            unprocessedOutputs.clear();
            LOGGER.info("Updated game state: " + this); // TODO debug?
            try {
                // Sleep, to be able to tell what is going on
                Thread.sleep(10L); // TODO remove this sleep
            } catch (InterruptedException e) {
                LOGGER.error("Unexpected interrupt", e);
                Thread.currentThread().interrupt();
            }
        } else {
            unprocessedOutputs.add(Long.valueOf(code));
        }
    }
    
    /** @return input for the Intcode computer */
    long getInput() {
        Optional<Point> ballLocation = getLocation(Tile.BALL);
        Optional<Point> paddleLocation = getLocation(Tile.PADDLE);
        
        long result;
        if (ballLocation.equals(paddleLocation) || ballLocation.isEmpty() || paddleLocation.isEmpty()) {
            LOGGER.debug("Staying put.");
            result = 0L;
        } else if (ballLocation.orElseThrow().getX() < paddleLocation.orElseThrow().getX()) {
            // the ball is left of the paddle
            LOGGER.debug("Moving left.");
            result = -1L;
        } else {
            // the ball is right of the paddle
            LOGGER.debug("Moving right.");
            result = 1L;
        }
        inputs.add(Long.valueOf(result));
        return result;
    }
    
    private Optional<Point> getLocation(Tile tile) {
        return tiles.entrySet().stream()
                .filter(entry -> entry.getValue() == tile)
                .map(Entry::getKey)
                .findFirst();
    }
    
    long getScore() {
        return score;
    }
    
    List<Long> getInputs() {
        return inputs;
    }
    
    /** @return number of blocks */
    long countBlocks() {
        return tiles.values().stream()
                .filter(tile -> tile == Tile.BLOCK)
                .count();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Score: ");
        builder.append(score);
        builder.append("\n");

        int minX = Point.minX(tiles.keySet());
        int maxX = Point.maxX(tiles.keySet());
        int minY = Point.minY(tiles.keySet());
        int maxY = Point.maxY(tiles.keySet());
        
        for (int y = minY; y != maxY + 1; y++) {
            for (int x = minX; x != maxX + 1; x++) {
                builder.append(tiles.getOrDefault(new Point(x, y), Tile.EMPTY).getRepresentation());
            }
            builder.append("\n");
        }
        
        return builder.toString();
    }
}
