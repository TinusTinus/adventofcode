package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        var lines = linesStream.toList();
        var indexOfEmptyLine = lines.indexOf("");

        var warehouse = Warehouse.parse(lines.subList(0, indexOfEmptyLine));
        var directions = lines.stream()
                .skip(indexOfEmptyLine + 1)
                .collect(Collectors.joining())
                .chars()
                .mapToObj(c -> Direction.parse((char)c))
                .toList();
        
        for (var direction : directions) {
            warehouse = warehouse.attemptMove(direction);
        }
        
        return warehouse.sumOfBoxGPS();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day15-2024.txt");

        LOGGER.info(result);
    }
}
 