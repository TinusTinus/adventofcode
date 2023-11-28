package nl.mvdr.adventofcode.adventofcode2019.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AmplificationCircuitPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart1Test extends SolverTest<AmplificationCircuitPart1> {

    /** Constructor. */
    public AmplificationCircuitPart1Test() {
        super(AmplificationCircuitPart1.class);
    }
    
    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("43210", "example-day07-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("54321", "example-day07-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("65210", "example-day07-2019-2.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("368584", "input-day07-2019.txt"); 
    }
}
