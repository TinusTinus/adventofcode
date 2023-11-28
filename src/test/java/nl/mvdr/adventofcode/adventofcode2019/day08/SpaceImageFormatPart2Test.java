package nl.mvdr.adventofcode.adventofcode2019.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpaceImageFormatPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceImageFormatPart2Test extends SolverTest<SpaceImageFormatPart2> {

    /** Constructor. */
    public SpaceImageFormatPart2Test() {
        super(SpaceImageFormatPart2.class);
    }
    
    /**
     * Test case based on the example from the puzzle text.
     * 
     * Inspect the log for the actual decoded image; this should be:
     * <pre>
     *  #
     * # 
     * </pre>
     */
    @Test
    public void testExample() {
        assertSolution(new SpaceImageFormatPart2(2, 2), "null", "example-day08-2019-1.txt");
    }

    /**
     * Test case based on the accepted solution.
     * 
     * Inspect the log for the actual decoded image.
     * It should spell: "LRFKU".
     */
    @Test
    public void testSolution() {
        testSolution("null", "input-day08-2019.txt"); 
    }
}
