package nl.mvdr.adventofcode.adventofcode2024.day12;

import nl.mvdr.adventofcode.point.Point;

import java.util.Set;

record Region(char plant, Set<Point> plots) {

    private int area() {
        return plots.size();
    }

    private long perimeter() {
        return plots.stream()
                .flatMap(plot -> plot.neighbours().stream().filter(neighbour -> !plots.contains(neighbour))).count();
    }

    long cost() {
        return Math.multiplyExact(area(), perimeter());
    }
    
    private long sides() {
        return 0; // TODO
    }
    
    long costWithDiscount() {
        return Math.multiplyExact(area(), sides());
    }
}
