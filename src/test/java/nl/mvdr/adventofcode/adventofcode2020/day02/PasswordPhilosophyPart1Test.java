package nl.mvdr.adventofcode.adventofcode2020.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PasswordPhilosophyPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PasswordPhilosophyPart1Test extends SolverTest<PasswordPhilosophyPart1> {

    /** Constructor. */
    public PasswordPhilosophyPart1Test() {
        super(PasswordPhilosophyPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In the above example, 2 passwords are valid. The middle password, cdefg, is
     * not; it contains no instances of b, but needs at least 1. The first and third
     * passwords are valid: they contain one a or nine c, both within the limits of
     * their respective policies.
     */
    @Test
    public void testExample() {
        testSolution("2", "example-day02-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("548", "input-day02-2020.txt"); 
    }
}
