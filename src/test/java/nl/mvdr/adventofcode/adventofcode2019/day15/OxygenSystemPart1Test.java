package nl.mvdr.adventofcode.adventofcode2019.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OxygenSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class OxygenSystemPart1Test extends SolverTest<OxygenSystemPart1> {

    /** Constructor. */
    public OxygenSystemPart1Test() {
        super(OxygenSystemPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("318", "input-day15-2019.txt"); 
    }
}
