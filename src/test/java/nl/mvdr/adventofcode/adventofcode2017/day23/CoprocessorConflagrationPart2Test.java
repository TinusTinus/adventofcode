package nl.mvdr.adventofcode.adventofcode2017.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CoprocessorConflagrationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart2Test extends SolverTest<CoprocessorConflagrationPart2> {

    /** Constructor. */
    public CoprocessorConflagrationPart2Test() {
        super(CoprocessorConflagrationPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("917", "input-day23-2017.txt");
    }
}
