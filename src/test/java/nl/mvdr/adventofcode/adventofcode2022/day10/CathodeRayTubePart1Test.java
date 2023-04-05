package nl.mvdr.adventofcode.adventofcode2022.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CathodeRayTubePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CathodeRayTubePart1Test extends SolverTest<CathodeRayTubePart1> {

    /** Constructor. */
    public CathodeRayTubePart1Test() {
        super(CathodeRayTubePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("131400", "example-day10-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day10-2022.txt");
    }
}
