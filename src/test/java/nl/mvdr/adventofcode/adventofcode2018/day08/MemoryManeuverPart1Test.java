package nl.mvdr.adventofcode.adventofcode2018.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MemoryManeuverPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryManeuverPart1Test extends SolverTest<MemoryManeuverPart1> {
    /** Constructor. */
    public MemoryManeuverPart1Test() {
        super(MemoryManeuverPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("138", "example-day08-2018.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void test() {
        assertSolution("40984", "input-day08-2018.txt");
    }
}
