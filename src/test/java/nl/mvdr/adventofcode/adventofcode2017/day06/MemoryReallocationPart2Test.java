package nl.mvdr.adventofcode.adventofcode2017.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MemoryReallocationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MemoryReallocationPart2Test extends SolverTest<MemoryReallocationPart2> {

    /** Constructor. */
    public MemoryReallocationPart2Test() {
        super(MemoryReallocationPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("4", "example-day06-2017.txt");
    }
    
    /** Test case based on the accepted slution. */
    @Test
    public void testSolution() {
        assertSolution("2793", "input-day06-2017.txt");
    }
}
