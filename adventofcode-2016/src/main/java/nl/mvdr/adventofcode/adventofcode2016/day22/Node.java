package nl.mvdr.adventofcode.adventofcode2016.day22;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

record Node(Point location, int size, int used, int avail, int usePercentage) {
    
    static Set<Node> parse(Stream<String> lines) {
        return lines.skip(2)
                .map(Node::parse)
                .collect(Collectors.toSet());
    }
    
    private static Node parse(String line) {
        var parts = line.split(" +");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Unable to parse as a node: " + line);
        }
        
        var location = parseLocation(parts[0]);
        var size = parseSize(parts[1]);
        var used = parseSize(parts[2]);
        var avail = parseSize(parts[3]);
        var usePercentage = parsePercentage(parts[4]);
        
        return new Node(location, size, used, avail, usePercentage);
    }

    private static Point parseLocation(String filename) {
        if (!filename.startsWith("/dev/grid/node-x")) {
            throw new IllegalArgumentException("Unable to parse as a filename: " + filename);
        }
        var filenameParts = filename.substring(16).split("-y");
        if (filenameParts.length != 2) {
            throw new IllegalArgumentException("Unable to determine node coordinates: " + filename);
        }
        var x = Integer.parseInt(filenameParts[0]);
        var y = Integer.parseInt(filenameParts[1]);
        return new Point(x, y);
    }
    
    private static int parseSize(String string) {
        if (!string.endsWith("T")) {
            throw new IllegalArgumentException("Unable to parse as a file size: " + string);
        }
        return Integer.parseInt(string.substring(0, string.length() - 1));
    }
    
    private static int parsePercentage(String string) {
        if (!string.endsWith("%")) {
            throw new IllegalArgumentException("Unable to parse as a percentage: " + string);
        }
        return Integer.parseInt(string.substring(0, string.length() - 1));
    }
    
    boolean isEmpty() {
        return used == 0;
    }
}
