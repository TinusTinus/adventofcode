package nl.mvdr.adventofcode.adventofcode2022.day16;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProboscideaVolcaniumPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart2Test extends SolverTest<ProboscideaVolcaniumPart2> {

    /** Constructor. */
    public ProboscideaVolcaniumPart2Test() {
        super(ProboscideaVolcaniumPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("1707", "example-day16-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Disabled // This test case takes like half an hour to run
    @Test
    public void testSolution() {
        assertSolution("2576", "input-day16-2022.txt");
    }
}
