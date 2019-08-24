package nl.mvdr.adventofcode.adventofcode2017.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link KnotHashPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart1Test extends SolverTest<KnotHashPart1> {

    /** Constructor. */
    public KnotHashPart1Test() {
        super(KnotHashPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new KnotHashPart1(5), "12", "example-day10-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1980", "input-day10-2017.txt");
    }
}
