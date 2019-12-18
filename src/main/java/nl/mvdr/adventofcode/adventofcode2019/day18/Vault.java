package nl.mvdr.adventofcode.adventofcode2019.day18;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the state of an underground vault.
 *
 * @author Martijn van de Rijdt
 */
class Vault {

    /** Current location. */
    private final Point startingPoint;
    
    /**
     * Open passages, which can be freely traveled through.
     * 
     * These include the starting point and key locations,
     * but not walls and closed doors.
     */
    private final Set<Point> openPassages;

    /**
     * Doors for which the key has not yet been obtained.
     * 
     * Values in this map are uppercase characters, corresponding to the lowercase key.
     */
    private final Map<Point, Character> closedDoors;
    
    /**
     * Locations of keys, which have not yet been obtained.
     * 
     * Values in this map are lowercase characters.
     */
    private final Map<Point, Character> keys;

    static Vault parse(Stream<String> linesStream) {
        Optional<Point> startingPoint = Optional.empty();
        Set<Point> openPassages = new HashSet<>();
        Map<Point, Character> closedDoors = new HashMap<>();
        Map<Point, Character> keys = new HashMap<>();
        
        List<String> lines = linesStream.collect(Collectors.toList());
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                Point point = new Point(x, y);
                char c = line.charAt(x);
                if (c == '@' && startingPoint.isPresent()) {
                    throw new IllegalArgumentException("Multiple starting points found.");
                } else if (c == '@') {
                    startingPoint = Optional.of(point);
                    openPassages.add(point);
                } else if (c == '.') {
                    openPassages.add(point);
                } else if (Character.isLowerCase(c)) {
                    keys.put(point, Character.valueOf(c));
                    openPassages.add(point);
                } else if (Character.isUpperCase(c)) {
                    closedDoors.put(point, Character.valueOf(c));
                } else if (c != '#') {
                    throw new IllegalArgumentException("Unexpected input: " + c);
                }
            }
        }
        
        return new Vault(startingPoint.orElseThrow(),
                Collections.unmodifiableSet(openPassages),
                Collections.unmodifiableMap(closedDoors),
                Collections.unmodifiableMap(keys));
        
    }
    
    /**
     * Constructor.
     * 
     * @param startingPoint starting point
     * @param openPassages open passages
     * @param closedDoors closed doors
     * @param keys keys
     */
    private Vault(Point startingPoint, Set<Point> openPassages, Map<Point, Character> closedDoors,
            Map<Point, Character> keys) {
        super();
        this.startingPoint = startingPoint;
        this.openPassages = openPassages;
        this.closedDoors = closedDoors;
        this.keys = keys;
    }
}
