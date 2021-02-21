package nl.mvdr.adventofcode.adventofcode2020.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrabCombatPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCombatPart1Test extends SolverTest<CrabCombatPart1> {

    /** Constructor. */
    public CrabCombatPart1Test() {
        super(CrabCombatPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("306", "example-day22-2020-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("33559", "input-day22-2020.txt");
    }
}
