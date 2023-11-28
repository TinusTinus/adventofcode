package nl.mvdr.adventofcode.adventofcode2017.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SeriesOfTubesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart1Test extends SolverTest<SeriesOfTubesPart1> {

    /** Constructor. */
    public SeriesOfTubesPart1Test() {
        super(SeriesOfTubesPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("ABCDEF", "example-day19-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("SXWAIBUZY", "input-day19-2017.txt");
    }
}
