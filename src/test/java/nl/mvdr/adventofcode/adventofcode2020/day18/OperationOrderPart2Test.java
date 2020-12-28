package nl.mvdr.adventofcode.adventofcode2020.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OperationOrderPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class OperationOrderPart2Test extends SolverTest<OperationOrderPart2> {

    /** Constructor. */
    public OperationOrderPart2Test() {
        super(OperationOrderPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("231", "example-day18-2020-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("51", "example-day18-2020-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("46", "example-day18-2020-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("1445", "example-day18-2020-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("669060", "example-day18-2020-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("23340", "example-day18-2020-5.txt");
    }
    
    /** Test case based on the examples from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("694173", "example-day18-2020-6.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("231235959382961", "input-day18-2020.txt");
    }
}
