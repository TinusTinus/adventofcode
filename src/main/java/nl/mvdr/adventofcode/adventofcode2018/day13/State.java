package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Current state of the map and the carts on it.
 *
 * @author Martijn van de Rijdt
 */
class State {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /** The map. */
    private final TrackSection[][] map;
    
    /** All mine carts. */
    private final Set<MineCart> carts;
    
    /**
     * Constructor.
     * 
     * @param map map
     * @param carts mine carts
     */
    private State(TrackSection[][] map, Set<MineCart> carts) {
        super();
        
        this.map = map;
        this.carts = carts;
    }
    
    State tick() {
        // TODO
        return this;
    }
    
    /**
     * Parses the input file into a state.
     * 
     * @param inputFilePath path of the input file
     * @return initial state
     * @throws IOException if the input could not be read
     */
    static State parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
        
        int height = lines.size();
        int width = lines.get(0).length();
        
        TrackSection[][] map = new TrackSection[width][height];
        Set<MineCart> carts = new HashSet<>();
        
        for (int y = 0; y != height; y++) {
            String line = lines.get(y);
            for (int x = 0; x != width; x++) {
                char c = line.charAt(x);
                
                Optional<MineCart> cart = parseMineCart(x, y, c);
                
                cart.ifPresent(carts::add);
                
                map[x][y] = cart.map(MineCart::getDirection)
                        .map(Direction::getStraightPath)
                        .orElseGet(() -> TrackSection.of(c));
            }
        }
        
        LOGGER.info("Parsed a {} x {} map with {} mine carts.", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(carts.size()));
        
        return new State(map, carts);
    }
    
    /**
     * Parses the given character as a mine cart.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param c character representation of the mine cart's direction
     * @return mine cart, or empty in case the given character does not match a {@link Direction} value
     */
    private static Optional<MineCart> parseMineCart(int x, int y, char c) {
        return Direction.of(c)
                .map(direction -> new MineCart(x, y, direction));
    }
}
