package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.Solver;
import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DonutMazePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DonutMazePart2Test extends SolverTest<DonutMazePart2> {

    /** Constructor. */
    public DonutMazePart2Test() {
        super(DonutMazePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("26", "example-day20-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        Solver solver = new DonutMazePart2();
        
        Assertions.assertThrows(NoSuchElementException.class, () -> solver.solve("example-day20-2019-1.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("396", "example-day20-2019-2.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day20-2019.txt"); 
    }
}
