package nl.mvdr.adventofcode.adventofcode2019.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpringdroidAdventurePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpringDroidAdventurePart2Test extends SolverTest<SpringdroidAdventurePart2> {

    /** Constructor. */
    public SpringDroidAdventurePart2Test() {
        super(SpringdroidAdventurePart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1141869516", "input-day21-2019.txt"); 
    }
}
