package nl.mvdr.adventofcode.adventofcode2017.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegistersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RegistersPart2Test extends SolverTest<RegistersPart2> {

    /** Constructor. */
    public RegistersPart2Test() {
        super(RegistersPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("10", "example-day08-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("5590", "input-day08-2017.txt");
    }
}
