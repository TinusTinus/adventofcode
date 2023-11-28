package nl.mvdr.adventofcode.adventofcode2022.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GrovePositioningSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GrovePositioningSystemPart2Test extends SolverTest<GrovePositioningSystemPart2> {

    /** Constructor. */
    public GrovePositioningSystemPart2Test() {
        super(GrovePositioningSystemPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("1623178306", "example-day20-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("8927480683", "input-day20-2022.txt");
    }
}
