package nl.mvdr.adventofcode.adventofcode2017.day21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link FractalArt}.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArtTest {

    /**
     * Test case for the initial state.
     * 
     * The program should always begin with the following pattern:
     * 
     * <pre>
     * .#.
     * ..#
     * ###
     * </pre>
     * 
     * So, regardless of the input, after 0 iterations the number of pixels that are on
     * should be 5 (the number of #s).
     */
    @Test
    public void testExample0Iterations() {
        assertSolution(new FractalArt(0), "5", "example-day21-2017.txt");
    }

    /** Test case based on an intermediate result from the example. */
    @Test
    public void testExample1Iterations() {
        assertSolution(new FractalArt(1), "4", "example-day21-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Iterations() {
        assertSolution(new FractalArt(2), "12", "example-day21-2017.txt");
    }
    
    /**
     * Invokes the solver and checks that the given solution matches the expected
     * result.
     * 
     * @param solver           solver under test
     * @param expectedSolution expected solution
     * @param inputfile        input file on the (test) classpath
     */
    private void assertSolution(FractalArt solver, String expectedSolution, String inputfile) {
        String result = solver.solve(inputfile);

        Assertions.assertEquals(expectedSolution, result);
    }
}
