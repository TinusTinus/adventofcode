package nl.mvdr.adventofcode.adventofcode2022.day08;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TreetopTreeHousePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TreetopTreeHousePart1Test extends SolverTest<TreetopTreeHousePart1> {

    /** Constructor. */
    public TreetopTreeHousePart1Test() {
        super(TreetopTreeHousePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("21", "example-day08-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1798", "input-day08-2022.txt");
    }
}
