package nl.mvdr.adventofcode.adventofcode2020.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ShuttleSearchPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart2Test extends SolverTest<ShuttleSearchPart2> {

    /** Constructor. */
    public ShuttleSearchPart2Test() {
        super(ShuttleSearchPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("1068781", "example-day13-2020-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("3417", "example-day13-2020-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("754018", "example-day13-2020-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("779210", "example-day13-2020-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("1261476", "example-day13-2020-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("1202161486", "example-day13-2020-5.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("672754131923874", "input-day13-2020.txt");
    }
}
