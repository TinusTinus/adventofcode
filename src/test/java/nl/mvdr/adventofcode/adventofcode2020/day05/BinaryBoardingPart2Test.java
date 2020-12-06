package nl.mvdr.adventofcode.adventofcode2020.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BinaryBoardingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryBoardingPart2Test extends SolverTest<BinaryBoardingPart2> {

    /** Constructor. */
    public BinaryBoardingPart2Test() {
        super(BinaryBoardingPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("597", "input-day05-2020.txt");
    }
}
