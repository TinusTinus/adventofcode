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
    
    /**
     * Test case based on a modified version of example 5 from the puzzle.
     * 
     * The input contains only elves, so combat should end immediately before a single round can complete.
     * 
     * Expected result is; 0 * total hitpoints = 0.
     */
    @Test
    public void testExampleOneUnit() {
        assertSolution("0", "example-day15-2018-6.txt");
    }
    
    
    /**
     * Test case based on a modified version of example 5 from the puzzle.
     * 
     * The input contains two units who start in adjacent squares.
     * 
     * The units should not move, and instead only attack each other.
     * It is expected to take 200 / 3 = 66 rounds to whittle down one unit's health.
     * The remaining unit is expected to have 200 % 3 = 2 hit points remaining.
     * Expected result: 66 * 2 = 132. 
     */
    @Test
    public void testExampleNoMovement() {
        assertSolution("132", "example-day15-2018-7.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Disabled // TODO add test expectation
    @Test
    public void testSolution() {
        assertSolution("?", "input-day15-2018.txt");
    }
}
