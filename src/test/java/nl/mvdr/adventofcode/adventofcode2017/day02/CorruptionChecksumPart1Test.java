package nl.mvdr.adventofcode.adventofcode2017.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CorruptionChecksumPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart1Test extends SolverTest<CorruptionChecksumPart1> {

    /** Constructor. */
    public CorruptionChecksumPart1Test() {
        super(CorruptionChecksumPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("18", "example-day02-2017.txt");
    }
    
    /** Test case based on the accepted slution. */
    @Test
    public void testSolution() {
        assertSolution("46402", "input-day02-2017.txt");
    }
}
