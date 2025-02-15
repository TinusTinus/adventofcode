package nl.mvdr.adventofcode.adventofcode2021.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link BinaryDiagnosticPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryDiagnosticPart1Test extends SolverTest<BinaryDiagnosticPart1> {

    /** Constructor. */
    public BinaryDiagnosticPart1Test() {
        super(BinaryDiagnosticPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("198", "example-day03-2021.txt"));
    }
}
