package nl.mvdr.adventofcode.adventofcode2023.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrebuchetPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TrebuchetPart1Test extends SolverTest<TrebuchetPart1> {

    /** Constructor. */
    public TrebuchetPart1Test() {
        super(TrebuchetPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("142", "example-day01-2023.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("55108", "input-day01-2023.txt");
    }
}
