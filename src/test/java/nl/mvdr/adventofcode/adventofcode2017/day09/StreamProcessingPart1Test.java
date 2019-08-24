package nl.mvdr.adventofcode.adventofcode2017.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link StreamProcessingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class StreamProcessingPart1Test extends SolverTest<StreamProcessingPart1> {

    /** Constructor. */
    public StreamProcessingPart1Test() {
        super(StreamProcessingPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("1", "example-day09-2017-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("6", "example-day09-2017-1.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("5", "example-day09-2017-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("16", "example-day09-2017-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("1", "example-day09-2017-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("9", "example-day09-2017-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("9", "example-day09-2017-6.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7() {
        assertSolution("3", "example-day09-2017-7.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("11898", "input-day09-2017.txt");
    }
}
