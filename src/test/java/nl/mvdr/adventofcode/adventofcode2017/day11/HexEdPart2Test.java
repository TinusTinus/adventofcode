package nl.mvdr.adventofcode.adventofcode2017.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HexEdPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart2Test extends SolverTest<HexEdPart2> {

    /** Constructor. */
    public HexEdPart2Test() {
        super(HexEdPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1524", "input-day11-2017.txt");
    }
}
