package nl.mvdr.adventofcode.adventofcode2021.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

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

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("198", "example-day03-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("2595824", "input-day03-2021.txt");
    }
}
