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

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("9581917", "input-day02-2019.txt"); 
    }
}
