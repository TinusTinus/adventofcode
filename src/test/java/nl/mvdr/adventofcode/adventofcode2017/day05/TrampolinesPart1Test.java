package nl.mvdr.adventofcode.adventofcode2017.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrampolinesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TrampolinesPart1Test extends SolverTest<TrampolinesPart1> {

    /** Constructor. */
    public TrampolinesPart1Test() {
        super(TrampolinesPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("5", "example-day05-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("358131", "input-day05-2017.txt");
    }
}
