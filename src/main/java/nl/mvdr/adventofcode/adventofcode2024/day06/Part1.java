package nl.mvdr.adventofcode.adventofcode2024.day06;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> lines) {
        State initialState = State.parse(lines.toList());
        State endState = initialState.predictPath();
        return endState.visited()
                .stream()
                .map(GuardPosition::position)
                .distinct()
                .count();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day06-2024.txt");

        LOGGER.info(result);
    }
}
 