package nl.mvdr.adventofcode.adventofcode2019.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SlamShufflePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SlamShufflePart1Test extends SolverTest<SlamShufflePart1> {

    /** Constructor. */
    public SlamShufflePart1Test() {
        super(SlamShufflePart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("3324", "input-day22-2019.txt");
    }
}
