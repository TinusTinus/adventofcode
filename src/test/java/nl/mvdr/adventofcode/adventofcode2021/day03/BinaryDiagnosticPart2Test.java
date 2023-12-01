package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BinaryDiagnosticPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryDiagnosticPart2Test extends SolverTest<BinaryDiagnosticPart2> {

    /** Constructor. */
    public BinaryDiagnosticPart2Test() {
        super(BinaryDiagnosticPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("230", "example-day03-2021.txt"),
                Arguments.of("2135254", "input-day03-2021.txt"));
    }
}
