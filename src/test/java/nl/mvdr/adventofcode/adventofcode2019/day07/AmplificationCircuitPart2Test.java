package nl.mvdr.adventofcode.adventofcode2019.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AmplificationCircuitPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart2Test extends SolverTest<AmplificationCircuitPart2> {

    /** Constructor. */
    public AmplificationCircuitPart2Test() {
        super(AmplificationCircuitPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("139629729", "example-day07-2019-3.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("18216", "example-day07-2019-4.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("35993240", "input-day07-2019.txt"); 
    }
}
