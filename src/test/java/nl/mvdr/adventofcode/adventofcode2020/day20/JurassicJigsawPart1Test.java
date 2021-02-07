package nl.mvdr.adventofcode.adventofcode2020.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link JurassicJigsawPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart1Test extends SolverTest<JurassicJigsawPart1> {

    /** Constructor. */
    public JurassicJigsawPart1Test() {
        super(JurassicJigsawPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("20899048083289", "example-day20-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("51214443014783", "input-day20-2020.txt");
    }
}
