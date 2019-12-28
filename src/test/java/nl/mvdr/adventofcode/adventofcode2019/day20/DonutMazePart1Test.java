package nl.mvdr.adventofcode.adventofcode2019.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DonutMazePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DonutMazePart1Test extends SolverTest<DonutMazePart1> {

    /** Constructor. */
    public DonutMazePart1Test() {
        super(DonutMazePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("23", "example-day20-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("58", "example-day20-2019-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("620", "input-day20-2019.txt"); 
    }
}
