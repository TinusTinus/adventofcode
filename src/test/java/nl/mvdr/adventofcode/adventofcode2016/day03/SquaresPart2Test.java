package nl.mvdr.adventofcode.adventofcode2016.day03;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SquaresPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart2Test extends SolverTest<SquaresPart2> {

    /** Constructor. */
    public SquaresPart2Test() {
        super(SquaresPart2.class);
    }

    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1921", "input-day03-2016.txt");
    }
}
