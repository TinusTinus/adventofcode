package nl.mvdr.adventofcode.adventofcode2018.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MineCartMadness}.
 *
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessTest extends SolverTest<MineCartMadness> {
    /** Constructor. */
    public MineCartMadnessTest() {
        super(MineCartMadness.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample00() {
        assertSolution("0,3", "example-day13-2018-00.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample01() {
        assertSolution("0,3", "example-day13-2018-01.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample02() {
        assertSolution("7,3", "example-day13-2018-02.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("???", "input-day13-2018.txt");
    }
}
