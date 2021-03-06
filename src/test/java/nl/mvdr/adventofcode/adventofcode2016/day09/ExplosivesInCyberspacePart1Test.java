package nl.mvdr.adventofcode.adventofcode2016.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ExplosivesInCyberspacePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart1Test extends SolverTest<ExplosivesInCyberspacePart1> {

    /** Constructor. */
    public ExplosivesInCyberspacePart1Test() {
        super(ExplosivesInCyberspacePart1.class);
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
    public void testExample4() {
        assertSolution("6", "example-day09-2016-4.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("18", "example-day09-2016-5.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("183269", "input-day09-2016.txt");
    }
}
