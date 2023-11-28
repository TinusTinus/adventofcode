package nl.mvdr.adventofcode.adventofcode2022.day14;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegolithReservoirPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RegolithReservoirPart2Test extends SolverTest<RegolithReservoirPart2> {

    /** Constructor. */
    public RegolithReservoirPart2Test() {
        super(RegolithReservoirPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("93", "example-day14-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        testSolution("27155", "input-day14-2022.txt");
    }
}
