package nl.mvdr.adventofcode.adventofcode2018.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ChronalCalibrationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart1Test extends SolverTest<ChronalCalibrationPart1> {

    /** Constructor. */
    public ChronalCalibrationPart1Test() {
        super(ChronalCalibrationPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("3", "example-day01-2018-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("3", "example-day01-2018-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("0", "example-day01-2018-2.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("-6", "example-day01-2018-3.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("425", "input-day01-2018.txt");
    }
}
