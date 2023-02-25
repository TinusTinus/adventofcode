package nl.mvdr.adventofcode.adventofcode2022.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RucksackReorganizationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RucksackReorganizationPart2Test extends SolverTest<RucksackReorganizationPart2> {

    /** Constructor. */
    public RucksackReorganizationPart2Test() {
        super(RucksackReorganizationPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("70", "example-day03-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("2581", "input-day03-2022.txt");
    }
}
