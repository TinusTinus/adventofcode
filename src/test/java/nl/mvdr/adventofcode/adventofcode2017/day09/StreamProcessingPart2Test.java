package nl.mvdr.adventofcode.adventofcode2017.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link StreamProcessingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class StreamProcessingPart2Test extends SolverTest<StreamProcessingPart2> {

    /** Constructor. */
    public StreamProcessingPart2Test() {
        super(StreamProcessingPart2.class);
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("5601", "input-day09-2017.txt");
    }
}
