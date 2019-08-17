package nl.mvdr.adventofcode.adventofcode2017.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrampolinesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TrampolinesPart2Test extends SolverTest<TrampolinesPart2> {

    /** Constructor. */
    public TrampolinesPart2Test() {
        super(TrampolinesPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("10", "example-day05-2017.txt");
    }
    
    /** Test case based on the accepted slution. */
    @Test
    public void testSolution() {
        assertSolution("25558839", "input-day05-2017.txt");
    }
}
