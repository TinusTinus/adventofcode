package nl.mvdr.adventofcode.adventofcode2017.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PermutationPromenadePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PermutationPromenadePart2Test extends SolverTest<PermutationPromenadePart2> {

    /** Constructor. */
    public PermutationPromenadePart2Test() {
        super(PermutationPromenadePart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("abocefghijklmndp", "input-day16-2017.txt");
    }
}
