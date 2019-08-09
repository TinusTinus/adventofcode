package nl.mvdr.adventofcode.adventofcode2018.day24;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ImmuneSystemSimulatorPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ImmuneSystemSimulatorPart1Test extends SolverTest<ImmuneSystemSimulatorPart1> {
    /** Constructor. */
    public ImmuneSystemSimulatorPart1Test() {
        super(ImmuneSystemSimulatorPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("5216", "example-day24-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day24-2018.txt");
    }
}
