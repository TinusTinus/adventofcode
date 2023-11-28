package nl.mvdr.adventofcode.adventofcode2022.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RockPaperScissorsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RockPaperScissorsPart2Test extends SolverTest<RockPaperScissorsPart2> {

    /** Constructor. */
    public RockPaperScissorsPart2Test() {
        super(RockPaperScissorsPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("12", "example-day02-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("12091", "input-day02-2022.txt");
    }
}
