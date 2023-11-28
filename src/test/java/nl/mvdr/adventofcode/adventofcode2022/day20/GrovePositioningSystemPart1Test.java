package nl.mvdr.adventofcode.adventofcode2022.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GrovePositioningSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class GrovePositioningSystemPart1Test extends SolverTest<GrovePositioningSystemPart1> {

    /** Constructor. */
    public GrovePositioningSystemPart1Test() {
        super(GrovePositioningSystemPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("3", "example-day20-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("2215", "input-day20-2022.txt");
    }
}
