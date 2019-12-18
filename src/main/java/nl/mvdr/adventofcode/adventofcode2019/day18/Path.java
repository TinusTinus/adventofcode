package nl.mvdr.adventofcode.adventofcode2019.day18;

import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the shortest path between two points.
 *
 * @author Martijn van de Rijdt
 */
class Path {
    private final Point start;
    private final Point finish;
    private final int length;
    private final Set<Character> requiredKeys;

    /**
     * Constructor.
     * 
     * @param start starting point
     * @param finish target
     * @param length length of the path
     * @param requiredKeys keys required to open any doors along the way of this path
     */
    Path(Point start, Point finish, int length, Set<Character> requiredKeys) {
        super();
        this.start = start;
        this.finish = finish;
        this.length = length;
        this.requiredKeys = requiredKeys;
    }
    
    Point getStart() {
        return start;
    }
    
    Point getFinish() {
        return finish;
    }
    
    int getLength() {
        return length;
    }
    
    Set<Character> getRequiredKeys() {
        return requiredKeys;
    }
}
