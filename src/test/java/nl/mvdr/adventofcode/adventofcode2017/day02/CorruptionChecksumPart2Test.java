package nl.mvdr.adventofcode.adventofcode2017.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CorruptionChecksumPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart2Test extends SolverTest<CorruptionChecksumPart2> {

    /** Constructor. */
    public CorruptionChecksumPart2Test() {
        super(CorruptionChecksumPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("9", "example-day02-2017-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("265", "input-day02-2017.txt");
    }
}
