package nl.mvdr.adventofcode.adventofcode2020.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CustomCustomsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CustomCustomsPart2Test extends SolverTest<CustomCustomsPart2> {

    /** Constructor. */
    public CustomCustomsPart2Test() {
        super(CustomCustomsPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * This list represents answers from five groups:
     * 
     * <ul>
     * <li>In the first group, everyone (all 1 person) answered "yes" to 3
     * questions: a, b, and c.</li>
     * <li>In the second group, there is no question to which everyone answered
     * "yes".</li>
     * <li>In the third group, everyone answered yes to only 1 question, a. Since
     * some people did not answer "yes" to b or c, they don't count.</li>
     * <li>In the fourth group, everyone answered yes to only 1 question, a.</li>
     * <li>In the fifth group, everyone (all 1 person) answered "yes" to 1 question,
     * b.</li>
     * </ul>
     * 
     * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
     */
    @Test
    public void testExample() {
        testSolution("6", "example-day06-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("3476", "input-day06-2020.txt");
    }
}
