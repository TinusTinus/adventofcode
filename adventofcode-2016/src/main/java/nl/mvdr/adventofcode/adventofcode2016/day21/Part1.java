package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part1 implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final String input;
    
    Part1(String input) {
        this.input = input;
    }
    
    public Part1() {
        this("abcdefgh");
    }
    
    @Override
    public String solve(Stream<String> lines) {
        var scrambler = Scrambler.parse(lines);
        return scrambler.apply(input);
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day21.txt");

        LOGGER.info(result);
    }
}
