package nl.mvdr.adventofcode.adventofcode2019.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CategorySixPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CategorySixPart2Test extends SolverTest<CategorySixPart2> {

    /** Constructor. */
    public CategorySixPart2Test() {
        super(CategorySixPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("19498", "input-day23-2019.txt");
    }
}
