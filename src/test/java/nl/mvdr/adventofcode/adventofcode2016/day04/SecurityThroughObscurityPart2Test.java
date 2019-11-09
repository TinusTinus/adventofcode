package nl.mvdr.adventofcode.adventofcode2016.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SecurityThroughObscurityPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SecurityThroughObscurityPart2Test extends SolverTest<SecurityThroughObscurityPart2> {

    /** Constructor. */
    public SecurityThroughObscurityPart2Test() {
        super(SecurityThroughObscurityPart2.class);
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("324", "input-day04-2016.txt");
    }
}
