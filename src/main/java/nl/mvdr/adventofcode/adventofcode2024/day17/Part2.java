package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public int solve(Stream<String> lines) {
        var program = Program.parse(lines.toList());
        
        var programString = program.program()
                .stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(","));
        
        return IntStream.iterate(0, a -> a + 1)
                .filter(a -> programString.equals(program.withInitialA(a).execute()))
                .findFirst()
                .orElseThrow();
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day17-2024.txt");

        LOGGER.info(result);
    }
}
 