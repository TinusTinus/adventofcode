package nl.mvdr.adventofcode.adventofcode2021.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GiantSquidPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GiantSquidPart2Test extends SolverTest<GiantSquidPart2> {

    /** Constructor. */
    public GiantSquidPart2Test() {
        super(GiantSquidPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("1924", "example-day04-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("23042", "input-day04-2021.txt");
    }
}
