package nl.mvdr.adventofcode.adventofcode2017.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link KnotHashPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart2Test extends SolverTest<KnotHashPart2> {

    /** Constructor. */
    public KnotHashPart2Test() {
        super(KnotHashPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("a2582a3a0e66e6e86e3812dcb672a272", "example-day10-2017-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("33efeb34ea91902bb2f59c9920caa6cd", "example-day10-2017-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("3efbe78a8d82f29979031a4aa0b16a9d", "example-day10-2017-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("63960835bcdc130f0b66d7ff4f6a5a8e", "example-day10-2017-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day10-2017.txt");
    }
}
