package nl.mvdr.adventofcode.adventofcode2017.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegistersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RegistersPart1Test extends SolverTest<RegistersPart1> {

    /** Constructor. */
    public RegistersPart1Test() {
        super(RegistersPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("1", "example-day08-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("4647", "input-day08-2017.txt");
    }
}
