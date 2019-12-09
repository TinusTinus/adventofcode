package nl.mvdr.adventofcode.adventofcode2019.day09;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SensorBoostPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SensorBoostPart1Test extends SolverTest<SensorBoostPart1> {

    /** Constructor. */
    public SensorBoostPart1Test() {
        super(SensorBoostPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("3638931938", "input-day09-2019.txt"); 
    }
}
