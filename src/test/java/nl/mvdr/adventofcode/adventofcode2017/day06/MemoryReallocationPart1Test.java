package nl.mvdr.adventofcode.adventofcode2017.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MemoryReallocationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryReallocationPart1Test extends SolverTest<MemoryReallocationPart1> {

    /** Constructor. */
    public MemoryReallocationPart1Test() {
        super(MemoryReallocationPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("5", "example-day06-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("4074", "input-day06-2017.txt");
    }
}
