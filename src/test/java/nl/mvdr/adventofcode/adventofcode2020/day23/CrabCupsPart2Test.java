package nl.mvdr.adventofcode.adventofcode2020.day23;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrabCupsPart2}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // solution is very inefficient
public class CrabCupsPart2Test extends SolverTest<CrabCupsPart2> {

    /** Constructor. */
    public CrabCupsPart2Test() {
        super(CrabCupsPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("149245887792", "example-day23-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("?", "input-day23-2020.txt");
    }
}
