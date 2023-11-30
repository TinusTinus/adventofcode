package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FractalArt}.
 *
 * @author Martijn van de Rijdt
 */
public class FractalArtTest extends SolverTest<FractalArt> {

    /** Constructor. */
    public FractalArtTest() {
        super(FractalArt.class);
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
        assertSolution(new FractalArt(0), "5", "input-day21-2017.txt");
    }
    
    /** Test case for the initial state. */
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
    
    /** Test case based on the accepted solution. */
    @Test
    public void testPart1Solution() {
        assertSolution(new FractalArt(5), "155", "input-day21-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testPart2Solution() {
        assertSolution(new FractalArt(18), "2449665", "input-day21-2017.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("155", "input-day21-2017.txt"));
    }
}
