package nl.mvdr.adventofcode.adventofcode2022.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NoSpaceLeftOnDevicePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class NoSpaceLeftOnDevicePart2Test extends SolverTest<NoSpaceLeftOnDevicePart2> {

    /** Constructor. */
    public NoSpaceLeftOnDevicePart2Test() {
        super(NoSpaceLeftOnDevicePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("24933642", "example-day07-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("4183246", "input-day07-2022.txt");
    }
}
