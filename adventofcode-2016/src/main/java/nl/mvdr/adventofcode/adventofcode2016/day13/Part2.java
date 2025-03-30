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

public class Part2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        var layout = MazeLayout.parse(lines);
        
        // Dijkstra's algorithm
        Set<Point> visited = new HashSet<>();
        
        Set<Point> current = new HashSet<>();
        current.add(new Point(1, 1));
        
        int pathLength = 0;
        
        while (pathLength <= 50) {
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
        
        return visited.size();
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day13.txt");

        LOGGER.info(result);
    }
}
