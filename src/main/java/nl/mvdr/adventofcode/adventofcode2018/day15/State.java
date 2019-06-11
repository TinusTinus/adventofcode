package nl.mvdr.adventofcode.adventofcode2018.day15;

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
 * State of the game.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /** The static map, consisting of walls and open areas. */
    private final Square[][] map;
    /** Currently alive units. */
    private final Set<Unit> units;
    
    /**
     * Constructor.
     * 
     * @param map static map, consisting of walls and open areas
     * @param units currently alive units
     */
    private State(Square[][] map, Set<Unit> units) { 
        super();
        this.map = map;
        this.units = units;
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
        
        Square[][] map = new Square[width][height];
        Set<Unit> units = new HashSet<>();
        
        for (int y = 0; y != height; y++) {
            String line = lines.get(y);
            for (int x = 0; x != width; x++) {
                char c = line.charAt(x);
                
                Optional<Unit> unit = parseUnit(x, y, c);
                
                unit.ifPresent(units::add);
                
                map[x][y] = unit.map(u -> Square.OPEN_AREA)
                        .orElseGet(() -> Square.of(c));
            }
        }
        
        State result = new State(map, units);
        LOGGER.debug("Parsed state:\n{}", result);
        return result;
    }
    
    /**
     * Creates a new unit with the given info.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param c character representation of the unit's race
     * @return new unit with full hit points, or empty if c does not correspond to a {@link Race} value
     */
    private static Optional<Unit> parseUnit(int x, int y, char c) {
        return Race.of(c)
                .map(race -> new Unit(race, x, y));
    }
}
