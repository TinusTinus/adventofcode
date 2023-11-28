package nl.mvdr.adventofcode.adventofcode2019.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CarePackagePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart2Test extends SolverTest<CarePackagePart2> {

    /** Constructor. */
    public CarePackagePart2Test() {
        super(CarePackagePart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("17336", "input-day13-2019.txt"); 
    }
}
