package nl.mvdr.adventofcode.adventofcode2020.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RambunctiousRecitationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RambunctiousRecitationPart1Test extends SolverTest<RambunctiousRecitationPart1> {

    /** Constructor. */
    public RambunctiousRecitationPart1Test() {
        super(RambunctiousRecitationPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("436", "example-day15-2020-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("1", "example-day15-2020-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("10", "example-day15-2020-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("27", "example-day15-2020-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("78", "example-day15-2020-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        testSolution("438", "example-day15-2020-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        testSolution("1836", "example-day15-2020-6.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("412", "input-day15-2020.txt");
    }
}
