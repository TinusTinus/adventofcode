package nl.mvdr.adventofcode.adventofcode2019.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProgramAlarmPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ProgramAlarmPart2Test extends SolverTest<ProgramAlarmPart2> {

    /** Constructor. */
    public ProgramAlarmPart2Test() {
        super(ProgramAlarmPart2.class);
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("2505", "input-day02-2019.txt"); 
    }
}
