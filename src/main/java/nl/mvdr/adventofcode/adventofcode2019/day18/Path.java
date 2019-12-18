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
    private final Set<Character> keysOnTheWay;

    /**
     * Constructor.
     * 
     * @param start starting point
     * @param finish target
     * @param length length of the path
     * @param requiredKeys keys required to open any doors along the way of this path
     * @param keysOnTheWay keys encountered along the path (other than start and finish)
     */
    Path(Point start, Point finish, int length, Set<Character> requiredKeys, Set<Character> keysOnTheWay) {
        super();
        this.start = start;
        this.finish = finish;
        this.length = length;
        this.requiredKeys = requiredKeys;
        this.keysOnTheWay = keysOnTheWay;
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
    
    Set<Character> getKeysOnTheWay() {
        return keysOnTheWay;
    }
}
