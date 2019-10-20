package nl.mvdr.adventofcode.adventofcode2017.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CoprocessorConflagrationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SporificaVirusPart1Test extends SolverTest<CoprocessorConflagrationPart1> {

    /** Constructor. */
    public SporificaVirusPart1Test() {
        super(CoprocessorConflagrationPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day23-2017.txt");
    }
}