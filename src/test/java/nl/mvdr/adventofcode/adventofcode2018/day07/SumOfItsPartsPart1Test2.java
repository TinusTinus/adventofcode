package nl.mvdr.adventofcode.adventofcode2018.day07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SumOfItsPartsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsPart1Test2 extends SolverTest<SumOfItsPartsPart2> {
    /** Constructor. */
    public SumOfItsPartsPart1Test2() {
        super(SumOfItsPartsPart2.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    @Disabled
    public void testExample() {
        assertSolution("15", "example-day07-2018.txt");
    }
}
