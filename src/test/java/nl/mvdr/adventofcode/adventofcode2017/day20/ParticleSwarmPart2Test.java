package nl.mvdr.adventofcode.adventofcode2017.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ParticleSwarmPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart2Test extends SolverTest<ParticleSwarmPart2> {

    /** Constructor. */
    public ParticleSwarmPart2Test() {
        super(ParticleSwarmPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("2", "example-day20-2017-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("1", "example-day20-2017-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day20-2017.txt");
    }
}
