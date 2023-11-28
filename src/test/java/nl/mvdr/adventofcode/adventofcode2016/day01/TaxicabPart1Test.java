package nl.mvdr.adventofcode.adventofcode2016.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TaxicabPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TaxicabPart1Test extends SolverTest<TaxicabPart1> {

    /** Constructor. */
    public TaxicabPart1Test() {
        super(TaxicabPart1.class);
    }

    /** Test case based on the first example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("5", "example-day01-2016-0.txt");
    }

    /** Test case based on the second example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("2", "example-day01-2016-1.txt");
    }

    /** Test case based on the third example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("12", "example-day01-2016-2.txt");
    }
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("230", "input-day01-2016.txt");
    }
}
