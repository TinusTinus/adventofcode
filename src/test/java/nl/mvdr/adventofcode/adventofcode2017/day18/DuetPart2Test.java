package nl.mvdr.adventofcode.adventofcode2017.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuetPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart2Test extends SolverTest<DuetPart2> {

    /** Constructor. */
    public DuetPart2Test() {
        super(DuetPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("3", "example-day18-2017-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("6858", "input-day18-2017.txt");
    }
}
