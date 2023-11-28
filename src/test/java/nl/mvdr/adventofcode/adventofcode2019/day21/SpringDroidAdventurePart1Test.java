package nl.mvdr.adventofcode.adventofcode2019.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpringdroidAdventurePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpringDroidAdventurePart1Test extends SolverTest<SpringdroidAdventurePart1> {

    /** Constructor. */
    public SpringDroidAdventurePart1Test() {
        super(SpringdroidAdventurePart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("19359752", "input-day21-2019.txt"); 
    }
}
