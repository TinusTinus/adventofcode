package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part2 implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    private final String input;
    
    Part2(String input) {
        this.input = input;
    }
    
    public Part2() {
        this("fbgdceah");
    }
    
    @Override
    public String solve(Stream<String> lines) {
        var scrambler = Scrambler.parse(lines);
        return scrambler.reverse().apply(input);
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day21.txt");

        LOGGER.info(result);
    }
}
