package nl.mvdr.adventofcode.adventofcode2018.day01;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ChronalCalibrationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart2Test extends SolverTest<ChronalCalibrationPart2> {

    /** Constructor. */
    public ChronalCalibrationPart2Test() {
        super(ChronalCalibrationPart2.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("2", "example-day01-2018-0.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("0", "example-day01-2018-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        testSolution("10", "example-day01-2018-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        testSolution("5", "example-day01-2018-6.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7() {
        testSolution("14", "example-day01-2018-7.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // This takes several seconds.
    public void testSolution() {
        testSolution("57538", "input-day01-2018.txt");
    }
}
