package nl.mvdr.adventofcode.adventofcode2017.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpiralMemoryPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpiralMemoryPart1Test extends SolverTest<SpiralMemoryPart1> {

    /** Constructor. */
    public SpiralMemoryPart1Test() {
        super(SpiralMemoryPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("0", "example-day03-2017-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("3", "example-day03-2017-1.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("2", "example-day03-2017-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("31", "example-day03-2017-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("371", "input-day03-2017.txt");
    }
}
