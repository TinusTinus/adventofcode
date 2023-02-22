package nl.mvdr.adventofcode.adventofcode2022.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RockPaperScissorsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RockPaperScissorsPart1Test extends SolverTest<RockPaperScissorsPart1> {

    /** Constructor. */
    public RockPaperScissorsPart1Test() {
        super(RockPaperScissorsPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("15", "example-day02-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("14163", "input-day02-2022.txt");
    }
}
