package nl.mvdr.adventofcode.adventofcode2017.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HexEdPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart1Test extends SolverTest<HexEdPart1> {

    /** Constructor. */
    public HexEdPart1Test() {
        super(HexEdPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("3", "example-day11-2017-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("0", "example-day11-2017-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("2", "example-day11-2017-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("3", "example-day11-2017-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("794", "input-day11-2017.txt");
    }
}
