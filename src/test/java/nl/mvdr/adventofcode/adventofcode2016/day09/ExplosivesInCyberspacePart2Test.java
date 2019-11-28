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
        assertSolution("6", "example-day09-2016-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("7", "example-day09-2016-1.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("9", "example-day09-2016-2.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("11", "example-day09-2016-3.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("20", "example-day09-2016-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("241920", "example-day09-2016-6.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7() {
        assertSolution("445", "example-day09-2016-7.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("11317278863", "input-day09-2016.txt");
    }
}
