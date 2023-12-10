package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the maze.
 *
 * @param start the animal's starting point
 * @param pipes the pipes in the maze, including the starting point
 * @param width the horizontal length of the maze
 * @param width the vertical length of the maze
 * @author Martijn van de Rijdt
 */
record Maze(Point start, Map<Point, Pipe> pipes, int width, int height) {
    
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
        var width = lines.getFirst().length();
        var height = lines.size();
        
        Point start = null;
        Map<Point, Pipe> pipes = new HashMap<>();
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            if (line.length() != width) {
                throw new IllegalArgumentException("Input is not rectangular");
            }
            for (var x = 0; x != width; x++) {
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
        pipes.put(start, findStartPipe(start, pipes));
        
        return new Maze(start, pipes, width, height);
    }

    /**
     * Determines the type of pipe at the starting point.
     * 
     * @param start starting point
     * @param pipes every other pipe
     * @return pipe at the starting point
     */
    private static Pipe findStartPipe(Point start, Map<Point, Pipe> pipes) {
        var directions = Stream.of(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN)
                .filter(direction -> connects(start, direction, pipes))
                .collect(Collectors.toSet());
        return Pipe.of(directions);
    }

    /**
     * Checks whether the given point connects to a pipe in the given direction.
     * 
     * @param point point
     * @param direction direction
     * @param pipes all known pipes
     * @return whether point has a connecting pipe in the given direction
     */
    private static boolean connects(Point point, Direction direction, Map<Point, Pipe> pipes) {
        var neighbour = direction.move(point);
        var neighbouringPipe = pipes.get(neighbour);
        return neighbouringPipe != null && neighbouringPipe.connectsTo(direction.reverse());
    }
    
    /**
     * @return the number of steps along the loop it takes to get from the starting position to the point farthest from the starting position
     */
    int computeMaxLoopDistance() {
        return findLoop().size() / 2;
    }
    
    /**
     * @return the loop containing the starting point
     */
    private List<Point> findLoop() {
        List<Point> result = new ArrayList<>();
        result.add(start);

        var startPipe = pipes.get(start);
        // Pick either starting direction. The problem is symmetrical.
        var direction = startPipe.getConnections().iterator().next();
        
        var location = direction.move(start);
        while (!result.getFirst().equals(location)) {
            var pipe = pipes.get(location);
            direction = pipe.findNextDirection(direction);
            location = direction.move(location);
            result.add(location);
        }
        
        return result;
    }

}
