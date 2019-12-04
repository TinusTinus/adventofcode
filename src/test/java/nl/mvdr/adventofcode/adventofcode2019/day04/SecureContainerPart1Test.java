package nl.mvdr.adventofcode.adventofcode2019.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SecureContainerPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart1Test extends SolverTest<SecureContainerPart1> {

    /** Constructor. */
    public SecureContainerPart1Test() {
        super(SecureContainerPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day04-2019.txt"); 
    }
}
