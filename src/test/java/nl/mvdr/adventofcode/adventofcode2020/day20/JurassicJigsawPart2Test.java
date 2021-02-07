package nl.mvdr.adventofcode.adventofcode2020.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link JurassicJigsawPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart2Test extends SolverTest<JurassicJigsawPart2> {

    /** Constructor. */
    public JurassicJigsawPart2Test() {
        super(JurassicJigsawPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("273", "example-day20-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day20-2020.txt"); // 2230 is too high
    }
}
