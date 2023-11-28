package nl.mvdr.adventofcode.adventofcode2018.day24;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ImmuneSystemSimulatorPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ImmuneSystemSimulatorPart2Test extends SolverTest<ImmuneSystemSimulatorPart2> {
    /** Constructor. */
    public ImmuneSystemSimulatorPart2Test() {
        super(ImmuneSystemSimulatorPart2.class);
    }

    /** Test case based on the accepted solution to the puzzle. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        testSolution("8812", "input-day24-2018.txt");
    }
}
