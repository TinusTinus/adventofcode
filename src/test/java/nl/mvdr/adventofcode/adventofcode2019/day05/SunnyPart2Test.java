package nl.mvdr.adventofcode.adventofcode2019.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SunnyPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SunnyPart2Test extends SolverTest<SunnyPart2> {

    /** Constructor. */
    public SunnyPart2Test() {
        super(SunnyPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("9006327", "input-day05-2019.txt"); 
    }
}
