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
        assertSolution("CABDFE", "example-day07-2018.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("PFKQWJSVUXEMNIHGTYDOZACRLB", "input-day07-2018.txt");
    }
}
