package nl.mvdr.adventofcode.adventofcode2022.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMapPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart2Test extends SolverTest<MonkeyMapPart2> {

    /** Constructor. */
    public MonkeyMapPart2Test() {
        super(MonkeyMapPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("5031", "example-day22-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day22-2022.txt");
    }
}
