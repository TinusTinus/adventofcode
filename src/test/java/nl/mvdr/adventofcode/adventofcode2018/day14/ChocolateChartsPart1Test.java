package nl.mvdr.adventofcode.adventofcode2018.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChocolateChartsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateChartsPart1Test extends SolverTest<ChocolateChartsPart1> {
    /** Constructor. */
    public ChocolateChartsPart1Test() {
        super(ChocolateChartsPart1.class);
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
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        assertSolution("1589167792", "example-day14-2018-4.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample5() {
        assertSolution("3710101245", "example-day14-2018-5.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("1474315445", "input-day14-2018.txt");
    }
}
