package nl.mvdr.adventofcode.adventofcode2020.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link EncodingErrorPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class EncodingErrorPart2Test extends SolverTest<EncodingErrorPart2> {

    /** Constructor. */
    public EncodingErrorPart2Test() {
        super(EncodingErrorPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     */
    @Test
    public void testExample() {
        assertSolution(new EncodingErrorPart2(5), "62", "example-day09-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("20532569", "input-day09-2020.txt");
    }
}
