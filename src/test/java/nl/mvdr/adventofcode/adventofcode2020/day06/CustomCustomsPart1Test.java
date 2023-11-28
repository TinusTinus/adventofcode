package nl.mvdr.adventofcode.adventofcode2020.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CustomCustomsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CustomCustomsPart1Test extends SolverTest<CustomCustomsPart1> {

    /** Constructor. */
    public CustomCustomsPart1Test() {
        super(CustomCustomsPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * This list represents answers from five groups:
     * 
     * <ul>
     * <li>The first group contains one person who answered "yes" to 3 questions: a,
     * b, and c.</li>
     * <li>The second group contains three people; combined, they answered "yes" to
     * 3 questions: a, b, and c.</li>
     * <li>The third group contains two people; combined, they answered "yes" to 3
     * questions: a, b, and c.</li>
     * <li>The fourth group contains four people; combined, they answered "yes" to
     * only 1 question, a.</li>
     * <li>The last group contains one person who answered "yes" to only 1 question,
     * b.</li>
     * </ul>
     * 
     * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
     */
    @Test
    public void testExample() {
        testSolution("11", "example-day06-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("6686", "input-day06-2020.txt");
    }
}
