package nl.mvdr.adventofcode.adventofcode2017.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PermutationPromenadePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PermutationPromenadePart1Test extends SolverTest<PermutationPromenadePart1> {

    /** Constructor. */
    public PermutationPromenadePart1Test() {
        super(PermutationPromenadePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new PermutationPromenadePart1(5), "baedc", "example-day16-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("ebjpfdgmihonackl", "input-day16-2017.txt");
    }
}
