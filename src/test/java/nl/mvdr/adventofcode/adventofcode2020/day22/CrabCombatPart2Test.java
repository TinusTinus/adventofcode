package nl.mvdr.adventofcode.adventofcode2020.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrabCombatPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCombatPart2Test extends SolverTest<CrabCombatPart2> {

    /** Constructor. */
    public CrabCombatPart2Test() {
        super(CrabCombatPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("291", "example-day22-2020-0.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExampleInfiniteRecursion() {
        int expectedScore = 2 * 43 + 19;
        assertSolution(Integer.toString(expectedScore), "example-day22-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("32789", "input-day22-2020.txt");
    }
}
