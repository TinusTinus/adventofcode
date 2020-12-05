package nl.mvdr.adventofcode.adventofcode2020.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ReportRepairPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ReportRepairPart2Test extends SolverTest<ReportRepairPart2> {

    /** Constructor. */
    public ReportRepairPart2Test() {
        super(ReportRepairPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying
     * them together produces 1721 * 299 = 514579, so the correct answer is 514579.
     */
    @Test
    public void testExample() {
        assertSolution("241861950", "example-day01-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("232508760", "input-day01-2020.txt"); 
    }
}
