package nl.mvdr.adventofcode.adventofcode2019.day18;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ManyWorldsInterpretationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ManyWorldsInterpretationPart1Test extends SolverTest<ManyWorldsInterpretationPart1> {

    /** Constructor. */
    public ManyWorldsInterpretationPart1Test() {
        super(ManyWorldsInterpretationPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("8", "example-day18-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("6", "example-day18-2019-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("0", "example-day18-2019-2.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("86", "example-day18-2019-3.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("132", "example-day18-2019-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("136", "example-day18-2019-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("81", "example-day18-2019-6.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day18-2019.txt"); 
    }
}
