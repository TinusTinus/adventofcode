package nl.mvdr.adventofcode.adventofcode2022.day17;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/17">Pyroclastic Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PyroclasticFlowPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PyroclasticFlowPart1.class);

    /** Width of the chamber. */
    private static final int WIDTH = 7;
    
    @Override
    public int solve(Stream<String> lines) {
        Queue<Direction> jetStream = lines.findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No input provided."))
                .chars()
                .mapToObj(c -> "" + (char)c)
                .map(Direction::parse)
                .collect(Collectors.toCollection(LinkedList::new));
        
        var shapes = Shape.initialShapeQueue();
        
        var rocksDropped = 0;
        Set<Point> settledRocks = new HashSet<>();
        Set<Point> fallingRock = new HashSet<>();
        
        // TODO actually perform the simulation
        
        return settledRocks.stream()
                .mapToInt(Point::y)
                .max()
                .orElseThrow();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new PyroclasticFlowPart1();

        var result = instance.solve("input-day17-2022.txt");

        LOGGER.info(result);
    }
}
 