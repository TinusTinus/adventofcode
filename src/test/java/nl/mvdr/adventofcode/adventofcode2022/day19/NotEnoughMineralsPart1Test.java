package nl.mvdr.adventofcode.adventofcode2022.day19;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NotEnoughMineralsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class NotEnoughMineralsPart1Test extends SolverTest<NotEnoughMineralsPart1> {

    /** Constructor. */
    public NotEnoughMineralsPart1Test() {
        super(NotEnoughMineralsPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    @Disabled // long-running test case
    public void testExample() {
        testSolution("33", "example-day19-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        testSolution("1480", "input-day19-2022.txt");
    }
}
