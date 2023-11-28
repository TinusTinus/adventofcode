package nl.mvdr.adventofcode.adventofcode2017.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpiralMemoryPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart2Test extends SolverTest<SpiralMemoryPart2> {

    /** Constructor. */
    public SpiralMemoryPart2Test() {
        super(SpiralMemoryPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("2", "example-day03-2017-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        testSolution("4", "example-day03-2017-5.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        testSolution("4", "example-day03-2017-6.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7() {
        testSolution("5", "example-day03-2017-7.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("369601", "input-day03-2017.txt");
    }
}
