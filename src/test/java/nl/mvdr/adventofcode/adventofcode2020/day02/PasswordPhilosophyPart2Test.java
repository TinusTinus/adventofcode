package nl.mvdr.adventofcode.adventofcode2020.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PasswordPhilosophyPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PasswordPhilosophyPart2Test extends SolverTest<PasswordPhilosophyPart2> {

    /** Constructor. */
    public PasswordPhilosophyPart2Test() {
        super(PasswordPhilosophyPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * Given the same example list from above:
     * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
     * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
     * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
     */
    @Test
    public void testExample() {
        testSolution("1", "example-day02-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("502", "input-day02-2020.txt");
    }
}
