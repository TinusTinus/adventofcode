package nl.mvdr.adventofcode.adventofcode2016.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BathroomSecurityPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BathroomSecurityPart1Test extends SolverTest<BathroomSecurityPart1> {

    /** Constructor. */
    public BathroomSecurityPart1Test() {
        super(BathroomSecurityPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("1985", "example-day02-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("38961", "input-day02-2016.txt");
    }
}
