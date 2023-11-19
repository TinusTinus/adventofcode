package nl.mvdr.adventofcode.adventofcode2021.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SonarSweepPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweepPart2Test extends SolverTest<SonarSweepPart2> {

    /** Constructor. */
    public SonarSweepPart2Test() {
        super(SonarSweepPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("5", "example-day01-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1471", "input-day01-2021.txt");
    }
}
