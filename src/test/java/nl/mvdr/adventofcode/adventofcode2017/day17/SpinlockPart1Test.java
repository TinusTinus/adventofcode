package nl.mvdr.adventofcode.adventofcode2017.day17;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpinlockPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart1Test extends SolverTest<SpinlockPart1> {

    /** Constructor. */
    public SpinlockPart1Test() {
        super(SpinlockPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("638", "example-day17-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("725", "input-day17-2017.txt");
    }
}
