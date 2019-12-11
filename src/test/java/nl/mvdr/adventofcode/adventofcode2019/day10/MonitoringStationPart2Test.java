package nl.mvdr.adventofcode.adventofcode2019.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonitoringStationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart2Test extends SolverTest<MonitoringStationPart2> {

    /** Constructor. */
    public MonitoringStationPart2Test() {
        super(MonitoringStationPart2.class);
    }
    
    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("802", "example-day10-2019-5.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day10-2019.txt"); 
    }
}
