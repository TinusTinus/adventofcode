package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.ArrayList;
import java.util.HashMap;
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

    /** Tiles making up the game. */
    private final Map<Point, Tile> tiles;
    /** Outputs received from the Intcode computer, which have not yet been processed into the game state. */
    private final List<Long> unprocessedOutputs;
    /** Current score. */
    private long score;

    /** Constructor. */
    GameState() {
        super();
        this.tiles = new HashMap<>();
        this.unprocessedOutputs = new ArrayList<>(2);
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
            LOGGER.debug("Updated game state: {}", this);
        } else {
            unprocessedOutputs.add(Long.valueOf(code));
        }
    }
    
    /** @return input for the Intcode computer */
    long getInput() {
        Point ballLocation = getLocation(Tile.BALL).orElseThrow();
        Point paddleLocation = getLocation(Tile.PADDLE).orElseThrow();
        
        long result;
        if (ballLocation.x() == paddleLocation.x()) {
            // the ball is above the paddle
            LOGGER.debug("Staying put.");
            result = 0L;
        } else if (ballLocation.x() < paddleLocation.x()) {
            // the ball is left of the paddle
            LOGGER.debug("Moving left.");
            result = -1L;
        } else {
            // the ball is right of the paddle
            LOGGER.debug("Moving right.");
            result = 1L;
        }
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
