package nl.mvdr.adventofcode.adventofcode2022.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMathPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart2Test extends SolverTest<MonkeyMathPart2> {

    /** Constructor. */
    public MonkeyMathPart2Test() {
        super(MonkeyMathPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("301", "example-day21-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day21-2022.txt");
    }
}
