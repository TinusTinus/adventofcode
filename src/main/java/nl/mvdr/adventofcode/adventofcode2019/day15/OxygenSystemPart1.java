package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 15 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/15">Oxygen System</a>.
 *
 * @author Martijn van de Rijdt
 */
public class OxygenSystemPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OxygenSystemPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of movement commands
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Map<Point, RepairDroid> visited = new HashMap<>();
        Map<Point, RepairDroid> unvisited = new HashMap<>();
        RepairDroid current = new RepairDroid(program);
        
        while (!current.isAtOxygenSystem()) {
            for (Direction direction : Direction.values()) {
                if (!visited.containsKey(direction.move(current.getLocation()))) {
                    current.step(direction)
                            .filter(next -> !unvisited.containsKey(next.getLocation()) || next.getPathLength() < unvisited.get(next.getLocation()).getPathLength())
                            .ifPresent(next -> unvisited.put(next.getLocation(), next));
                }
            }
            
            unvisited.remove(current.getLocation());
            visited.put(current.getLocation(), current);
            
            current = unvisited.values().stream()
                    .min(Comparator.comparing(RepairDroid::getPathLength))
                    .orElseThrow();
        }
        
        return current.getPathLength();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        OxygenSystemPart1 instance = new OxygenSystemPart1();

        String result = instance.solve("input-day15-2019.txt");

        LOGGER.info(result);
    }
}
 