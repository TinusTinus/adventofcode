package nl.mvdr.adventofcode.adventofcode2020.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DockingDataPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DockingDataPart2Test extends SolverTest<DockingDataPart2> {

    /** Constructor. */
    public DockingDataPart2Test() {
        super(DockingDataPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("208", "example-day14-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("4574598714592", "input-day14-2020.txt");
    }
}
