package nl.mvdr.adventofcode.adventofcode2019.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonitoringStationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart1Test extends SolverTest<MonitoringStationPart1> {

    /** Constructor. */
    public MonitoringStationPart1Test() {
        super(MonitoringStationPart1.class);
    }
    
    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("8", "example-day10-2019-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("33", "example-day10-2019-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("35", "example-day10-2019-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("41", "example-day10-2019-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("210", "example-day10-2019-4.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("256", "input-day10-2019.txt"); 
    }
}
