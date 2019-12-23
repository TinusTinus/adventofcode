package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
public class OxygenSystemPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OxygenSystemPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of minutes until the area is filled with oxygen
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        // Find the oxygen system's location by sending the droid there
        RepairDroid droid = new RepairDroid(program)
                .moveToOxygenSystem();
        
        // Simulate the oxygen filling the area by repair drones swarming into the area
        Map<Point, RepairDroid> visited = new HashMap<>();
        Map<Point, RepairDroid> unvisited = new HashMap<>();
        Optional<RepairDroid> current = Optional.of(new RepairDroid(droid.getProgram()));
        
        while (current.isPresent()) {
            for (Direction direction : Direction.values()) {
                if (!visited.containsKey(direction.move(current.orElseThrow().getLocation()))) {
                    current.orElseThrow()
                            .step(direction)
                            .filter(next -> !unvisited.containsKey(next.getLocation()) || next.getPathLength() < unvisited.get(next.getLocation()).getPathLength())
                            .ifPresent(next -> unvisited.put(next.getLocation(), next));
                }
            }
            
            unvisited.remove(current.orElseThrow().getLocation());
            visited.put(current.orElseThrow().getLocation(), current.orElseThrow());
            
            current = unvisited.values().stream()
                    .min(Comparator.comparing(RepairDroid::getPathLength));
        }
        
        return visited.values().stream()
                .mapToInt(RepairDroid::getPathLength)
                .max()
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        OxygenSystemPart2 instance = new OxygenSystemPart2();

        String result = instance.solve("input-day15-2019.txt");

        LOGGER.info(result);
    }
}
 