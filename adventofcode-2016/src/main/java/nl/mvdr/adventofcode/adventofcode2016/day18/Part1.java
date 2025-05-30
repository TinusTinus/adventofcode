package nl.mvdr.adventofcode.adventofcode2016.day18;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final int rows;
    
    Part1(int rows) {
        this.rows = rows;
    }
    
    public Part1() {
        this(40);
    }
    
    @Override
    public int solve(Stream<String> lines) {
        Set<Point> traps = new HashSet<>();
        
        var line = lines.findFirst().orElseThrow();
        IntStream.range(0, line.length())
                .filter(index -> line.charAt(index) == '^')
                .mapToObj(x -> new Point(x, 0))
                .forEach(traps::add);
        
        return 0; // TODO
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day18.txt");

        LOGGER.info(result);
    }
}
