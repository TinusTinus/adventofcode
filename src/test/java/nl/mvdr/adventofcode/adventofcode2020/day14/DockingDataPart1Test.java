package nl.mvdr.adventofcode.adventofcode2020.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DockingDataPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DockingDataPart1Test extends SolverTest<DockingDataPart1> {

    /** Constructor. */
    public DockingDataPart1Test() {
        super(DockingDataPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("165", "example-day14-2020-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("9628746976360", "input-day14-2020.txt");
    }
}
