package nl.mvdr.adventofcode.adventofcode2017.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SeriesOfTubesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart2Test extends SolverTest<SeriesOfTubesPart2> {

    /** Constructor. */
    public SeriesOfTubesPart2Test() {
        super(SeriesOfTubesPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("38", "example-day19-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("16676", "input-day19-2017.txt");
    }
}
