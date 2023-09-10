package nl.mvdr.adventofcode.adventofcode2022.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMapPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart1Test extends SolverTest<MonkeyMapPart1> {

    /** Constructor. */
    public MonkeyMapPart1Test() {
        super(MonkeyMapPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("6032", "example-day22-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day22-2022.txt"); // 1602 is too low
    }
}
