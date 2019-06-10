package nl.mvdr.adventofcode.adventofcode2018.day14;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChocolateCharts}.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateChartsTest extends SolverTest<ChocolateCharts> {
    /** Constructor. */
    public ChocolateChartsTest() {
        super(ChocolateCharts.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("5158916779", "example-day14-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("0124515891", "example-day14-2018-1.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("9251071085", "example-day14-2018-2.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("5941429882", "example-day14-2018-3.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    @Disabled // TODO
    public void testSolution() {
        // 8131753921 is not it (too high)
        assertSolution("???", "input-day14-2018.txt");
    }
}
