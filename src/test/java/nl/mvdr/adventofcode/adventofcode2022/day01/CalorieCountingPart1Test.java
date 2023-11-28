package nl.mvdr.adventofcode.adventofcode2022.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CalorieCountingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CalorieCountingPart1Test extends SolverTest<CalorieCountingPart1> {

    /** Constructor. */
    public CalorieCountingPart1Test() {
        super(CalorieCountingPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("24000", "example-day01-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("67027", "input-day01-2022.txt");
    }
}
