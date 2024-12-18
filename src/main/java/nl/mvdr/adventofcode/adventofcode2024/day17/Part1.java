package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public String solve(Stream<String> lines) {
        var program = Program.parse(lines.toList());
        return program.execute();
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day17-2024.txt");

        LOGGER.info(result);
    }
}
 