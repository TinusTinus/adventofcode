package nl.mvdr.adventofcode.adventofcode2021.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HydrothermalVenturePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HydrothermalVenturePart1Test extends SolverTest<HydrothermalVenturePart1> {

    /** Constructor. */
    public HydrothermalVenturePart1Test() {
        super(HydrothermalVenturePart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("5", "example-day05-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day05-2021.txt");
    }
}
