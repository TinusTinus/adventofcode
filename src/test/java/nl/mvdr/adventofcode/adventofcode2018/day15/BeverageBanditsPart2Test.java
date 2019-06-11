package nl.mvdr.adventofcode.adventofcode2018.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link BeverageBanditsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBanditsPart2Test extends SolverTest<BeverageBanditsPart2> {
    /** Constructor. */
    public BeverageBanditsPart2Test() {
        super(BeverageBanditsPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("4988", "example-day15-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("31284", "example-day15-2018-2.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("3478", "example-day15-2018-3.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        assertSolution("6474", "example-day15-2018-4.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample5() {
        assertSolution("1140", "example-day15-2018-5.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("66510", "input-day15-2018.txt");
    }
}
