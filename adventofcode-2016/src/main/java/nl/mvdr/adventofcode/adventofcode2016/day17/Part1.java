package nl.mvdr.adventofcode.adventofcode2016.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part1 implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public String solve(Stream<String> lines) {
        var passcode = lines.findFirst().orElseThrow();
        var initialState = new State(passcode);
        return initialState.shortestPathToVault();
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day17.txt");

        LOGGER.info(result);
    }
}
