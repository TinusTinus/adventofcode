package nl.mvdr.adventofcode.adventofcode2022.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NotEnoughMineralsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class NotEnoughMineralsPart2Test extends SolverTest<NotEnoughMineralsPart2> {

    /** Constructor. */
    public NotEnoughMineralsPart2Test() {
        super(NotEnoughMineralsPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("3472", "example-day19-2022.txt"); // 62 * 56
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day19-2022.txt"); // 6 * x * 12, where 32 <= x.  2088 is too low
    }
}
