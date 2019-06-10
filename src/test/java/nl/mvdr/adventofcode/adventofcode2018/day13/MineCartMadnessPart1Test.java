package nl.mvdr.adventofcode.adventofcode2018.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MineCartMadnessPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart1Test extends SolverTest<MineCartMadnessPart1> {
    /** Constructor. */
    public MineCartMadnessPart1Test() {
        super(MineCartMadnessPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("0,3", "example-day13-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("0,3", "example-day13-2018-1.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("7,3", "example-day13-2018-2.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("7,3", "example-day13-2018-3.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("124,130", "input-day13-2018.txt");
    }
}
