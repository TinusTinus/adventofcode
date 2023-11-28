package nl.mvdr.adventofcode.adventofcode2019.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OxygenSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class OxygenSystemPart2Test extends SolverTest<OxygenSystemPart2> {

    /** Constructor. */
    public OxygenSystemPart2Test() {
        super(OxygenSystemPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("390", "input-day15-2019.txt"); 
    }
}
