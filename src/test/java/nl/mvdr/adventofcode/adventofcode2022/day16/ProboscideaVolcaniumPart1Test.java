package nl.mvdr.adventofcode.adventofcode2022.day16;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProboscideaVolcaniumPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart1Test extends SolverTest<ProboscideaVolcaniumPart1> {

    /** Constructor. */
    public ProboscideaVolcaniumPart1Test() {
        super(ProboscideaVolcaniumPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("1651", "example-day16-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // long-running test case
    public void testSolution() {
        assertSolution("1896", "input-day16-2022.txt");
    }
}
