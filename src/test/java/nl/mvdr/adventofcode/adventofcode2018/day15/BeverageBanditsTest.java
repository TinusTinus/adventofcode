package nl.mvdr.adventofcode.adventofcode2018.day15;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link BeverageBandits}.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBanditsTest extends SolverTest<BeverageBandits> {
    /** Constructor. */
    public BeverageBanditsTest() {
        super(BeverageBandits.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("27730", "example-day15-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("36334", "example-day15-2018-1.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("39514", "example-day15-2018-2.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("27755", "example-day15-2018-3.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        assertSolution("28944", "example-day15-2018-4.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample5() {
        assertSolution("18740", "example-day15-2018-5.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Disabled // TODO add test expectation
    @Test
    public void testSolution() {
        assertSolution("?", "input-day15-2018.txt");
    }
}
