package nl.mvdr.adventofcode.adventofcode2022.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CalorieCountingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CalorieCountingPart2Test extends SolverTest<CalorieCountingPart2> {

    /** Constructor. */
    public CalorieCountingPart2Test() {
        super(CalorieCountingPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("45000", "example-day01-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("197291", "input-day01-2022.txt");
    }
}
