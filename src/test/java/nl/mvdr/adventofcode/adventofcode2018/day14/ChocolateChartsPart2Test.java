package nl.mvdr.adventofcode.adventofcode2018.day14;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChocolateChartsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateChartsPart2Test extends SolverTest<ChocolateChartsPart2> {
    /** Constructor. */
    public ChocolateChartsPart2Test() {
        super(ChocolateChartsPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample6() {
        testSolution("9", "example-day14-2018-6.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample7() {
        testSolution("5", "example-day14-2018-7.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample8() {
        testSolution("18", "example-day14-2018-8.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample9() {
        testSolution("2018", "example-day14-2018-9.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        testSolution("20278122", "input-day14-2018.txt");
    }
}
