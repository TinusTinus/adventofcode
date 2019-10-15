package nl.mvdr.adventofcode.adventofcode2017.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ParticleSwarmPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart1Test extends SolverTest<ParticleSwarmPart1> {

    /** Constructor. */
    public ParticleSwarmPart1Test() {
        super(ParticleSwarmPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("0", "example-day20-2017-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("119", "input-day20-2017.txt");
    }
}
