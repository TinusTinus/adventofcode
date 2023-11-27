package nl.mvdr.adventofcode.adventofcode2021.day03;

import org.junit.jupiter.api.Test;

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

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("230", "example-day03-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("2135254", "input-day03-2021.txt");
    }
}
