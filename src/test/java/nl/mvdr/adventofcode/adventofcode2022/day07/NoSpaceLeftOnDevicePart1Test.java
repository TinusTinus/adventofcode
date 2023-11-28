package nl.mvdr.adventofcode.adventofcode2022.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NoSpaceLeftOnDevicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class NoSpaceLeftOnDevicePart1Test extends SolverTest<NoSpaceLeftOnDevicePart1> {

    /** Constructor. */
    public NoSpaceLeftOnDevicePart1Test() {
        super(NoSpaceLeftOnDevicePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("95437", "example-day07-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1454188", "input-day07-2022.txt");
    }
}
