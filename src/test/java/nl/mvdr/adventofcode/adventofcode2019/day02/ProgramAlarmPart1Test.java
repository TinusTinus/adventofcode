package nl.mvdr.adventofcode.adventofcode2019.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProgramAlarmPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ProgramAlarmPart1Test extends SolverTest<ProgramAlarmPart1> {

    /** Constructor. */
    public ProgramAlarmPart1Test() {
        super(ProgramAlarmPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("2", "example-day02-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("2", "example-day02-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("2", "example-day02-2019-2.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("30", "example-day02-2019-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day02-2019.txt");
    }
}
