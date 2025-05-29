package nl.mvdr.adventofcode.adventofcode2016.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        var passcode = lines.findFirst().orElseThrow();
        var initialState = new State(passcode);
        return initialState.longestPathToVaultLength();
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day17.txt");

        LOGGER.info(result);
    }
}
