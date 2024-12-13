package nl.mvdr.adventofcode.adventofcode2024.day06;

import java.util.HashSet;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

record Area(int width, int height, Set<Point> obstructions) {
    boolean contains(Point point) {
        return 0 <= point.x() && point.x() < width
                && 0 <= point.y() && point.y() < height;
    }
    
    Area addObstruction(Point obstruction) {
        Set<Point> newObstructions = new HashSet<>(obstructions);
        newObstructions.add(obstruction);
        return new Area(width, height, newObstructions);
    }
}
