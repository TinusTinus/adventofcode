package nl.mvdr.adventofcode.adventofcode2022.day05;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SupplyStacksPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SupplyStacksPart2Test extends SolverTest<SupplyStacksPart2> {

    /** Constructor. */
    public SupplyStacksPart2Test() {
        super(SupplyStacksPart2.class);
    }

    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("MCD", "example-day05-2022.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("PRTTGRFPB", "input-day05-2022.txt");
    }
}
