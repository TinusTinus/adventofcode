package nl.mvdr.adventofcode.adventofcode2020.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OperationOrderPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class OperationOrderPart1Test extends SolverTest<OperationOrderPart1> {

    /** Constructor. */
    public OperationOrderPart1Test() {
        super(OperationOrderPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("71", "example-day18-2020-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("51", "example-day18-2020-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("26", "example-day18-2020-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("437", "example-day18-2020-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("12240", "example-day18-2020-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("13632", "example-day18-2020-5.txt");
    }
    
    /** Test case based on the examples from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("26457", "example-day18-2020-6.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day18-2020.txt");
    }
}
