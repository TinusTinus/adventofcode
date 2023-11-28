package nl.mvdr.adventofcode.adventofcode2022.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CampCleanupPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CampCleanupPart2Test extends SolverTest<CampCleanupPart2> {

    /** Constructor. */
    public CampCleanupPart2Test() {
        super(CampCleanupPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("4", "example-day04-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("833", "input-day04-2022.txt");
    }
}
