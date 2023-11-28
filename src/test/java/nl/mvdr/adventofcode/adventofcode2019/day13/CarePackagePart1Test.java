package nl.mvdr.adventofcode.adventofcode2019.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CarePackagePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart1Test extends SolverTest<CarePackagePart1> {

    /** Constructor. */
    public CarePackagePart1Test() {
        super(CarePackagePart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("344", "input-day13-2019.txt"); 
    }
}
