package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.solver.IntSolver;

abstract class WarehouseWoesSolver implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseWoesSolver.class);
    
    @Override
    public int solve(Stream<String> linesStream) {
        var lines = toList(linesStream);
        var indexOfEmptyLine = lines.indexOf("");

        var warehouse = Warehouse.parse(lines.subList(0, indexOfEmptyLine));
        LOGGER.debug("Initial {}", warehouse);
        
        var directions = lines.stream()
                .skip(indexOfEmptyLine + 1)
                .collect(Collectors.joining())
                .chars()
                .mapToObj(c -> Direction.parse((char)c))
                .toList();
        
        for (var direction : directions) {
            warehouse = warehouse.attemptMove(direction);
            LOGGER.debug("After attempted {} move: {}", direction, warehouse);
        }
        
        return warehouse.boxes()
                .stream()
                .mapToInt(Box::gps)
                .sum();
    }

    /// Enables subclasses to manipulate the input.
    protected List<String> toList(Stream<String> linesStream) {
        return linesStream.toList();
    }
}
 