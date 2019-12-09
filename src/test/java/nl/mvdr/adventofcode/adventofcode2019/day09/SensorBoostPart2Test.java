package nl.mvdr.adventofcode.adventofcode2019.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SensorBoostPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SensorBoostPart2Test extends SolverTest<SensorBoostPart2> {

    /** Constructor. */
    public SensorBoostPart2Test() {
        super(SensorBoostPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("86025", "input-day09-2019.txt"); 
    }
}
