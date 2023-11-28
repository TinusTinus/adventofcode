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
        testSolution("802", "example-day10-2019-4.txt");
    }
    
    /** The 1st asteroid to be vaporized is at 11,12. */
    @Test
    public void testExample1() {
        assertSolution(new MonitoringStationPart2(1), "1112", "example-day10-2019-4.txt");
    }
    
    /** The 2nd asteroid to be vaporized is at 12,1. */
    @Test
    public void testExample2() {
        assertSolution(new MonitoringStationPart2(2), "1201", "example-day10-2019-4.txt");
    }
    
    /** The 3rd asteroid to be vaporized is at 12,2. */
    @Test
    public void testExample3() {
        assertSolution(new MonitoringStationPart2(3), "1202", "example-day10-2019-4.txt");
    }
    
    /** The 10th asteroid to be vaporized is at 12,8. */
    @Test
    public void testExample10() {
        assertSolution(new MonitoringStationPart2(10), "1208", "example-day10-2019-4.txt");
    }
    
    /** The 20th asteroid to be vaporized is at 16,0. */
    @Test
    public void testExample20() {
        assertSolution(new MonitoringStationPart2(20), "1600", "example-day10-2019-4.txt");
    }
    
    /** The 50th asteroid to be vaporized is at 16,9. */
    @Test
    public void testExample50() {
        assertSolution(new MonitoringStationPart2(50), "1609", "example-day10-2019-4.txt");
    }
    
    /** The 100th asteroid to be vaporized is at 10,16. */
    @Test
    public void testExample100() {
        assertSolution(new MonitoringStationPart2(100), "1016", "example-day10-2019-4.txt");
    }
    
    /** The 199th asteroid to be vaporized is at 9,6. */
    @Test
    public void testExample199() {
        assertSolution(new MonitoringStationPart2(199), "906", "example-day10-2019-4.txt");
    }
    
    /** The 200th asteroid to be vaporized is at 8,2. */
    @Test
    public void testExample200() {
        assertSolution(new MonitoringStationPart2(200), "802", "example-day10-2019-4.txt");
    }
    
    /** The 201st asteroid to be vaporized is at 10,9. */
    @Test
    public void testExample201() {
        assertSolution(new MonitoringStationPart2(201), "1009", "example-day10-2019-4.txt");
    }
    
    /** The 299th and final asteroid to be vaporized is at 11,1. */
    @Test
    public void testExample299() {
        assertSolution(new MonitoringStationPart2(299), "1101", "example-day10-2019-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1707", "input-day10-2019.txt");
    }
}
