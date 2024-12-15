package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.solver.IntSolver;

abstract class WarehouseWoesSolver implements IntSolver {

    @Override
    public int solve(Stream<String> linesStream) {
        var lines = toList(linesStream);
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

    /// Enables subclasses to manipulate the input.
    protected List<String> toList(Stream<String> linesStream) {
        return linesStream.toList();
    }
}
 