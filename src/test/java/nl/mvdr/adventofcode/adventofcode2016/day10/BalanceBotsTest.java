package nl.mvdr.adventofcode.adventofcode2016.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BalanceBots}.
 *
 * @author Martijn van de Rijdt
 */
public class BalanceBotsTest extends SolverTest<BalanceBots> {

    /** Constructor. */
    public BalanceBotsTest() {
        super(BalanceBots.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new BalanceBots(5, 2), "2", "example-day10-2016.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExampleReversed() {
        assertSolution(new BalanceBots(2, 5), "2", "example-day10-2016.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day10-2016.txt");
    }
}
