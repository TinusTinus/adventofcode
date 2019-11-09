package nl.mvdr.adventofcode.adventofcode2016.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SignalsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SignalsPart1Test extends SolverTest<SignalsPart1> {

    /** Constructor. */
    public SignalsPart1Test() {
        super(SignalsPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("easter", "example-day06-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day06-2016.txt");
    }
}
