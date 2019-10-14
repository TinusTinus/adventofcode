package nl.mvdr.adventofcode.adventofcode2017.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuetPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart1Test extends SolverTest<DuetPart1> {

    /** Constructor. */
    public DuetPart1Test() {
        super(DuetPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("4", "example-day18-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("4601", "input-day18-2017.txt");
    }
}
