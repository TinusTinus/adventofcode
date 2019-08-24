package nl.mvdr.adventofcode.adventofcode2017.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link KnotHashPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart1Test extends SolverTest<KnotHashPart1> {

    /** Constructor. */
    public KnotHashPart1Test() {
        super(KnotHashPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new KnotHashPart1(5), "12", "example-day10-2017.txt");
    }
    
    /**
     * Test case based on the correct solution.
     * 
     * NOTE: the Advent of Code site rejected this answer, saying:
     * "That's not the right answer; your answer is too high."
     * 
     * However, other people's solutions using this input also get the same result.
     * I'm reasonably confident that this is actually the correct answer.
     */
    @Test
    public void testSolution() {
        assertSolution("14074", "input-day10-2017.txt");
    }
}
