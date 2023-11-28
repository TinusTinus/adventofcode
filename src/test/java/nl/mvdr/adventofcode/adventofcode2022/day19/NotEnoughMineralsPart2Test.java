package nl.mvdr.adventofcode.adventofcode2022.day19;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NotEnoughMineralsPart2}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // These test cases take a long time to run
public class NotEnoughMineralsPart2Test extends SolverTest<NotEnoughMineralsPart2> {

    /** Constructor. */
    public NotEnoughMineralsPart2Test() {
        super(NotEnoughMineralsPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("3472", "example-day19-2022.txt"); // 62 * 56
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("3168", "input-day19-2022.txt"); // 6 * 44 * 12
    }
}
