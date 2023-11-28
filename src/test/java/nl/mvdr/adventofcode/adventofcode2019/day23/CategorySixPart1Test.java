package nl.mvdr.adventofcode.adventofcode2019.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CategorySixPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CategorySixPart1Test extends SolverTest<CategorySixPart1> {

    /** Constructor. */
    public CategorySixPart1Test() {
        super(CategorySixPart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("26744", "input-day23-2019.txt");
    }
}
