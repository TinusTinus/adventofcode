package nl.mvdr.adventofcode.adventofcode2018.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SumOfItsParts}.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsTest extends SolverTest<SumOfItsParts> {
    /** Constructor. */
    public SumOfItsPartsTest() {
        super(SumOfItsParts.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("CABDFE", "example-day05-2018-0.txt");
    }
}
