package nl.mvdr.adventofcode.adventofcode2021.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GiantSquidPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class GiantSquidPart1Test extends SolverTest<GiantSquidPart1> {

    /** Constructor. */
    public GiantSquidPart1Test() {
        super(GiantSquidPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("4512", "example-day03-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day03-2021.txt");
    }
}
