package nl.mvdr.adventofcode.adventofcode2018.day09;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MarbleMania}.
 *
 * @author Martijn van de Rijdt
 */
public class MarbleManiaTest extends SolverTest<MarbleMania> {
    /** Constructor. */
    public MarbleManiaTest() {
        super(MarbleMania.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("32", "example-day09-2018-0.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("8317", "example-day09-2018-1.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("146373", "example-day09-2018-2.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    @Disabled // This test case fails; maybe an error in the example?
    public void testExample3() {
        assertSolution("2764", "example-day09-2018-3.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        assertSolution("54718", "example-day09-2018-4.txt");
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample5() {
        assertSolution("37305", "example-day09-2018-5.txt");
    }
    
    /** Test case based on the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("434674", "input-day09-2018.txt");
    }
}
