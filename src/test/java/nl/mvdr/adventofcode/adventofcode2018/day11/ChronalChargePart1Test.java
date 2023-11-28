package nl.mvdr.adventofcode.adventofcode2018.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalChargePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalChargePart1Test extends SolverTest<ChronalChargePart1> {
    /** Constructor. */
    public ChronalChargePart1Test() {
        super(ChronalChargePart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        testSolution("33,45", "example-day11-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        testSolution("21,61", "example-day11-2018-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("216,12", "input-day11-2018.txt");
    }
}
