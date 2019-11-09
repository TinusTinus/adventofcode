package nl.mvdr.adventofcode.adventofcode2016.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SecurityThroughObscurityPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SecurityThroughObscurityPart1Test extends SolverTest<SecurityThroughObscurityPart1> {

    /** Constructor. */
    public SecurityThroughObscurityPart1Test() {
        super(SecurityThroughObscurityPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("1514", "example-day04-2016.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("245102", "input-day04-2016.txt");
    }
}
