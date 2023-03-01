package nl.mvdr.adventofcode.adventofcode2022.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CampCleanupPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CampCleanupPart1Test extends SolverTest<CampCleanupPart1> {

    /** Constructor. */
    public CampCleanupPart1Test() {
        super(CampCleanupPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("2", "example-day04-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("494", "input-day04-2022.txt");
    }
}