package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the maze.
 *
 * @param start the animal's starting point
 * @param pipes the pipes in the maze, excluding the starting point
 * @author Martijn van de Rijdt
 */
record Maze(Point start, Map<Point, Pipe> pipes) {
    
    /** Representation of the starting position of the animal. */
    private static final char STARTING_POINT = 'S';
    /** Representation of a ground tile, where there is no pipe. */
    private static final char GROUND = '.';

    /**
     * Parses the textual representation of a pipe maze.
     * 
     * @param lines puzzle input
     * @return maze
     */
    static Maze parse(List<String> lines) {
        Point start = null;
        Map<Point, Pipe> pipes = new HashMap<>();
        for (var y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (var x = 0; x != line.length(); x++) {
                var character = line.charAt(x);
                if (character == STARTING_POINT) {
                    if (start != null) {
                        throw new IllegalArgumentException("Multiple starting points found");
                    }
                    start = new Point(x, y);
                } else if (character != GROUND) {
                    var pipe = Pipe.parse(character);
                    pipes.put(new Point(x, y), pipe);
                }
            }
        }
        if (start == null) {
            throw new IllegalArgumentException("No starting point found");
        }
        return new Maze(start, pipes);
    }
}
