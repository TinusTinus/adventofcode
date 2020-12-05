package nl.mvdr.adventofcode.adventofcode2020.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ReportRepairPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ReportRepairPart1Test extends SolverTest<ReportRepairPart1> {

    /** Constructor. */
    public ReportRepairPart1Test() {
        super(ReportRepairPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying
     * them together produces 1721 * 299 = 514579, so the correct answer is 514579.
     */
    @Test
    public void testExample() {
        assertSolution("514579", "example-day01-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("691771", "input-day01-2020.txt"); 
    }
}
