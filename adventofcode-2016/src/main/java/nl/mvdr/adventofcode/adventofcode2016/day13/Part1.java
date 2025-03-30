package nl.mvdr.adventofcode.adventofcode2016.day13;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final Point finish;
    
    Part1(Point finish) {
        this.finish = finish;
    }
    
    public Part1() {
        this(new Point(31, 39));
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var layout = MazeLayout.parse(lines);
        
        // Let's Dijkstra it up!
        Set<Point> visited = new HashSet<>();
        
        Set<Point> current = new HashSet<>();
        current.add(new Point(1, 1));
        
        int pathLength = 0;
        
        while (!current.contains(finish)) {
            if (current.isEmpty()) {
                throw new IllegalStateException("No path found.");
            }
            
            var next = current.stream()
                    .flatMap(point -> point.neighbours().stream())
                    .distinct()
                    .filter(Predicate.not(visited::contains))
                    .filter(layout::isOpenSpace)
                    .collect(Collectors.toSet());
            
            visited.addAll(current);
            current = next;
            pathLength++;
        }
        
        return pathLength;
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day13.txt");

        LOGGER.info(result);
    }
}
