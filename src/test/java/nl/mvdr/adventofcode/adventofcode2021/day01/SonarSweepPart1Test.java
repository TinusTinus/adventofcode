package nl.mvdr.adventofcode.adventofcode2021.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SonarSweepPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SonarSweepPart1Test extends SolverTest<SonarSweepPart1> {

    /** Constructor. */
    public SonarSweepPart1Test() {
        super(SonarSweepPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("7", "example-day01-2021.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1448", "input-day01-2021.txt");
    }
}
