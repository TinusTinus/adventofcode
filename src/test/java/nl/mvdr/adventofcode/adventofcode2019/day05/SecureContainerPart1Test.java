package nl.mvdr.adventofcode.adventofcode2019.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SunnyPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart1Test extends SolverTest<SunnyPart1> {

    /** Constructor. */
    public SecureContainerPart1Test() {
        super(SunnyPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("15508323", "input-day05-2019.txt"); 
    }
}
