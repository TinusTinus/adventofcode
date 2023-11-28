package nl.mvdr.adventofcode.adventofcode2016.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BathroomSecurityPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BathroomSecurityPart2Test extends SolverTest<BathroomSecurityPart2> {

    /** Constructor. */
    public BathroomSecurityPart2Test() {
        super(BathroomSecurityPart2.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("5DB3", "example-day02-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("46C92", "input-day02-2016.txt");
    }
}
