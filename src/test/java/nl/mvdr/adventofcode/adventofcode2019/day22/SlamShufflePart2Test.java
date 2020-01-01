package nl.mvdr.adventofcode.adventofcode2019.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SlamShufflePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SlamShufflePart2Test extends SolverTest<SlamShufflePart2> {

    /** Constructor. */
    public SlamShufflePart2Test() {
        super(SlamShufflePart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day22-2019.txt");
    }
}
