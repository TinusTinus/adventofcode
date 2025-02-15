package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.OptionalLong;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> lines) {
        var program = Program.parse(lines.toList());
        return findANaive(program)
                .orElseGet(() -> findA(program));
    }
    
    /// Naive solution.
    /// This solution works correctly for any input,
    /// but performance is not acceptable for the actual puzzle input,
    /// so results are capped to 200_000.
    private static OptionalLong findANaive(Program program) {
        return LongStream.range(0, 200_000)
                .filter(a -> program.withInitialA(a).execute().equals(program.program()))
                .findFirst();
    }

    /// More efficient solution for the actual puzzle input,
    /// using knowledge of the program it represents.
    private static long findA(Program program) {
        return LongStream.range(0, 8)
                .mapToObj(n -> findA(program, n))
                .filter(OptionalLong::isPresent)
                .mapToLong(OptionalLong::orElseThrow)
                .findFirst()
                .orElseThrow();
    }
    
    private static OptionalLong findA(Program program, long a) {
        OptionalLong result;
        
        var expectedOutput = program.program();
        
        var output = program.withInitialA(a).execute();
        var outputLength = output.size();
        
        if (output.equals(program.program())) {
            result = OptionalLong.of(a);
        } else if (expectedOutput.subList(expectedOutput.size() - outputLength, expectedOutput.size()).equals(output)) {
            result = LongStream.range(0, 8)
                    .mapToObj(n -> findA(program, 8 * a + n))
                    .filter(OptionalLong::isPresent)
                    .findFirst()
                    .orElse(OptionalLong.empty());
        } else {
            result = OptionalLong.empty();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day17-2024.txt");

        LOGGER.info(result);
    }
}
 