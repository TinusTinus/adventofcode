package nl.mvdr.adventofcode.adventofcode2017.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RecursiveCircusPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RecrusiveCircusPart2Test extends SolverTest<RecursiveCircusPart2> {

    /** Constructor. */
    public RecrusiveCircusPart2Test() {
        super(RecursiveCircusPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("60", "example-day07-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("362", "input-day07-2017.txt");
    }
}
