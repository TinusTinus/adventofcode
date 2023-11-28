package nl.mvdr.adventofcode.adventofcode2021.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HydrothermalVenturePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HydrothermalVenturePart2Test extends SolverTest<HydrothermalVenturePart2> {

    /** Constructor. */
    public HydrothermalVenturePart2Test() {
        super(HydrothermalVenturePart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("12", "example-day05-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day05-2021.txt"); // should be at least 4826
    }
}
