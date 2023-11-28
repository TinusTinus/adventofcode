package nl.mvdr.adventofcode.adventofcode2020.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandheldHaltingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HandheldHaltingPart1Test extends SolverTest<HandheldHaltingPart1> {

    /** Constructor. */
    public HandheldHaltingPart1Test() {
        super(HandheldHaltingPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * Immediately before the program would run an instruction a second time, the value in the accumulator is 5.
     * 
     */
    @Test
    public void testExample() {
        testSolution("5", "example-day08-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1654", "input-day08-2020.txt");
    }
}
