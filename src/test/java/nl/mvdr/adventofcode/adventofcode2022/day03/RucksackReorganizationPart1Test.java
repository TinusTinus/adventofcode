package nl.mvdr.adventofcode.adventofcode2022.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RucksackReorganizationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RucksackReorganizationPart1Test extends SolverTest<RucksackReorganizationPart1> {

    /** Constructor. */
    public RucksackReorganizationPart1Test() {
        super(RucksackReorganizationPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("157", "example-day03-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("7850", "input-day03-2022.txt");
    }
}
