package nl.mvdr.adventofcode.adventofcode2022.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TreetopTreeHousePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TreetopTreeHousePart2Test extends SolverTest<TreetopTreeHousePart2> {

    /** Constructor. */
    public TreetopTreeHousePart2Test() {
        super(TreetopTreeHousePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("8", "example-day08-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("259308", "input-day08-2022.txt");
    }
}
