package nl.mvdr.adventofcode.adventofcode2017.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpinlockPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart2Test extends SolverTest<SpinlockPart2> {

    /** Constructor. */
    public SpinlockPart2Test() {
        super(SpinlockPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("27361412", "input-day17-2017.txt");
    }
}
