package nl.mvdr.adventofcode.adventofcode2023.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link Day1Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class Day1Part1Test extends SolverTest<Day1Part1> {

    /** Constructor. */
    public Day1Part1Test() {
        super(Day1Part1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("?", "example-day01-2023.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day01-2023.txt");
    }
}
