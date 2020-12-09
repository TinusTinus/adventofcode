package nl.mvdr.adventofcode.adventofcode2020.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandheldHaltingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HandheldHaltingPart2Test extends SolverTest<HandheldHaltingPart2> {

    /** Constructor. */
    public HandheldHaltingPart2Test() {
        super(HandheldHaltingPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("8", "example-day08-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day08-2020.txt");
    }
}
