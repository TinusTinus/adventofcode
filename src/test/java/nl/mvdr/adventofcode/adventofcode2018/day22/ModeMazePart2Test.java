package nl.mvdr.adventofcode.adventofcode2018.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ModeMazePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ModeMazePart2Test extends SolverTest<ModeMazePart1> {
    /** Constructor. */
    public ModeMazePart2Test() {
        super(ModeMazePart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("45", "example-day22-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day22-2018.txt");
    }
}
