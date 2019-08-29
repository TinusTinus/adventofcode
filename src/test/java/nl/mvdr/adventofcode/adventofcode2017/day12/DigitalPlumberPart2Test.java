package nl.mvdr.adventofcode.adventofcode2017.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DigitalPlumberPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DigitalPlumberPart2Test extends SolverTest<DigitalPlumberPart2> {

    /** Constructor. */
    public DigitalPlumberPart2Test() {
        super(DigitalPlumberPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("2", "example-day12-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("171", "input-day12-2017.txt");
    }
}
