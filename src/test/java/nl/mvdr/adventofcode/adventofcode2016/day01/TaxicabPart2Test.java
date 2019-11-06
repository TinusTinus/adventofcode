package nl.mvdr.adventofcode.adventofcode2016.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TaxicabPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TaxicabPart2Test extends SolverTest<TaxicabPart2> {

    /** Constructor. */
    public TaxicabPart2Test() {
        super(TaxicabPart2.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("4", "example-day01-2016-3.txt");
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("154", "input-day01-2016.txt");
    }
}
