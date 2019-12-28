package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the maze.
 *
 * @author Martijn van de Rijdt
 */
class Maze {
    
    
    static Maze parse(List<String> lines) {
        // Build a map containing all open passages (marked as '.' on the map) as its keys,
        // and their labels as its values.
        Map<Point, Optional<String>> openPassages = new HashMap<>();
        
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); y++) {
                if (line.charAt(x) == '.') {
                    Point point = new Point(x, y);
                    
                    Optional<String> label;
                    if (Character.isUpperCase(line.charAt(x - 2)) && Character.isUpperCase(line.charAt(x - 2))) {
                        // there is a label to the left of this passage
                        label = Optional.of(line.substring(x - 2, x));
                    } else if (Character.isUpperCase(line.charAt(x + 1)) && Character.isUpperCase(line.charAt(x + 2))) {
                        // there is a label to the right of this passage
                        label = Optional.of(line.substring(x + 1, x + 3));
                    } else if (Character.isUpperCase(lines.get(y - 2).charAt(x)) && Character.isUpperCase(lines.get(y - 1).charAt(x))) {
                        // there is a label above this passage
                        label = Optional.of("" + Character.isUpperCase(lines.get(y - 2).charAt(x)) + Character.isUpperCase(lines.get(y - 1).charAt(x)));
                    } else if (Character.isUpperCase(lines.get(y + 1).charAt(x)) && Character.isUpperCase(lines.get(y + 2).charAt(x))) {
                        // there is a label below this passage
                        label = Optional.of("" + Character.isUpperCase(lines.get(y + 1).charAt(x)) + Character.isUpperCase(lines.get(y + 2).charAt(x)));
                    } else {
                        // no label found
                        label = Optional.empty();
                    }
                    
                    openPassages.put(point, label);
                }
            }
        }
        
        Point start = openPassages.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals("AA"))
                .map(Entry::getKey)
                .findFirst()
                .orElseThrow();
        
        Point finish = openPassages.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals("ZZ"))
                .map(Entry::getKey)
                .findFirst()
                .orElseThrow();
        
        return new Maze(); // TODO
    }
    
    private Maze() {
        
    }
}
