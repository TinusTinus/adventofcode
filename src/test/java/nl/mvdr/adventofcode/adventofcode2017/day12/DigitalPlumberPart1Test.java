package nl.mvdr.adventofcode.adventofcode2017.day12;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DigitalPlumberPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DigitalPlumberPart1Test extends SolverTest<DigitalPlumberPart1> {

    /** Constructor. */
    public DigitalPlumberPart1Test() {
        super(DigitalPlumberPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("6", "example-day12-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("141", "input-day12-2017.txt");
    }
}
