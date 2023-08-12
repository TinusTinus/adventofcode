package nl.mvdr.adventofcode.adventofcode2022.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NotEnoughMineralsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart1Test extends SolverTest<NotEnoughMineralsPart1> {

    /** Constructor. */
    public BoilingBouldersPart1Test() {
        super(NotEnoughMineralsPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("33", "example-day19-2022.txt"); // 9 + 24
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day19-2022.txt");
    }
}
