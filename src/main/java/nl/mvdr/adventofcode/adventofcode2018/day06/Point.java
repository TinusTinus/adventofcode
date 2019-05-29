package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A point in two dimensions.
 *
 * @author Martijn van de Rijdt
 * 
 */
class Point {

    private final int x;

    private final int y;

    private Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    // TODO javadoc
    static Set<Point> parse(Path path) throws IOException {
        return Files.lines(path)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(line -> line.split(", "))
                .map(array -> new Point(Integer.valueOf(array[0]), Integer.valueOf(array[1])))
                .collect(Collectors.toSet());
                        
    }
}
