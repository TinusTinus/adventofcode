package nl.mvdr.adventofcode.adventofcode2016.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SignalsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart2Test extends SolverTest<SignalsPart2> {

    /** Constructor. */
    public SignalsPart2Test() {
        super(SignalsPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("advent", "example-day06-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("pljvorrk", "input-day06-2016.txt");
    }
}
