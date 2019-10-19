package nl.mvdr.adventofcode.adventofcode2017.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FractalArtPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart1Test extends SolverTest<FractalArtPart1> {

    /** Constructor. */
    public ParticleSwarmPart1Test() {
        super(FractalArtPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0Iterations() {
        assertSolution(new FractalArtPart1(0), "5", "example-day21-2017.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Iterations() {
        assertSolution(new FractalArtPart1(1), "4", "example-day21-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Iterations() {
        assertSolution(new FractalArtPart1(2), "12", "example-day21-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void test0Iterations() {
        assertSolution(new FractalArtPart1(0), "5", "input-day21-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day21-2017.txt");
    }
}
