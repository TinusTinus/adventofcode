package nl.mvdr.adventofcode.adventofcode2023.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrebuchetPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TrebuchetPart2Test extends SolverTest<TrebuchetPart2> {

    /** Constructor. */
    public TrebuchetPart2Test() {
        super(TrebuchetPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExamplePart1() {
        assertSolution("142", "example-day01-2023-part1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExamplePart2() {
        assertSolution("281", "example-day01-2023-part2.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("56324", "input-day01-2023.txt");
    }
}
