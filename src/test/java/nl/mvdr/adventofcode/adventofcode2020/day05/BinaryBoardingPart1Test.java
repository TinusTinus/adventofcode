package nl.mvdr.adventofcode.adventofcode2020.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BinaryBoardingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryBoardingPart1Test extends SolverTest<BinaryBoardingPart1> {

    /** Constructor. */
    public BinaryBoardingPart1Test() {
        super(BinaryBoardingPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("801", "input-day05-2020.txt");
    }
}
