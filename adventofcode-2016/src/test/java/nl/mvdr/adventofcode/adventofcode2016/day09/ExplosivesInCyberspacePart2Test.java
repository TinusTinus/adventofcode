package nl.mvdr.adventofcode.adventofcode2016.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ExplosivesInCyberspacePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart2Test extends SolverTest<ExplosivesInCyberspacePart2> {

    /** Constructor. */
    public ExplosivesInCyberspacePart2Test() {
        super(ExplosivesInCyberspacePart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6", "example-day09-0.txt"),
                Arguments.of("7", "example-day09-1.txt"),
                Arguments.of("9", "example-day09-2.txt"),
                Arguments.of("11", "example-day09-3.txt"),
                Arguments.of("20", "example-day09-5.txt"),
                Arguments.of("241920", "example-day09-6.txt"),
                Arguments.of("445", "example-day09-7.txt"));
    }
}
