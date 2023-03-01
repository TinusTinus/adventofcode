package nl.mvdr.adventofcode.adventofcode2022.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SupplyStacksPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SupplyStacksPart1Test extends SolverTest<SupplyStacksPart1> {

    /** Constructor. */
    public SupplyStacksPart1Test() {
        super(SupplyStacksPart1.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("CMZ", "example-day05-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("ZRLJGSCTR", "input-day05-2022.txt");
    }
}
