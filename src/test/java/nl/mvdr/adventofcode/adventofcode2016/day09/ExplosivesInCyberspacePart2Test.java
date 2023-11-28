package nl.mvdr.adventofcode.adventofcode2016.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ExplosivesInCyberspacePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart2Test extends SolverTest<ExplosivesInCyberspacePart2> {

    /** Constructor. */
    public ExplosivesInCyberspacePart2Test() {
        super(ExplosivesInCyberspacePart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("6", "example-day09-2016-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("7", "example-day09-2016-1.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("9", "example-day09-2016-2.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("11", "example-day09-2016-3.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        testSolution("20", "example-day09-2016-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        testSolution("241920", "example-day09-2016-6.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7() {
        testSolution("445", "example-day09-2016-7.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("11317278863", "input-day09-2016.txt");
    }
}
