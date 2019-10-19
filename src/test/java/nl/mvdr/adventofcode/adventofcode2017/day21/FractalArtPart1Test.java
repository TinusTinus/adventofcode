package nl.mvdr.adventofcode.adventofcode2017.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FractalArtPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArtPart1Test extends SolverTest<FractalArtPart1> {

    /** Constructor. */
    public FractalArtPart1Test() {
        super(FractalArtPart1.class);
    }

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
    public void test0Iterations() {
        assertSolution(new FractalArtPart1(0), "5", "input-day21-2017.txt");
    }
    
    /** Test case for the initial state. */
    @Test
    public void testExample0Iterations() {
        assertSolution(new FractalArtPart1(0), "5", "example-day21-2017.txt");
    }

    /** Test case based on an intermediate result from the example. */
    @Test
    public void testExample1Iterations() {
        assertSolution(new FractalArtPart1(1), "4", "example-day21-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Iterations() {
        assertSolution(new FractalArtPart1(2), "12", "example-day21-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("155", "input-day21-2017.txt");
    }
}
