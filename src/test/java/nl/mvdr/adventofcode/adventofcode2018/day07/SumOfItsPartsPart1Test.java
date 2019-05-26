package nl.mvdr.adventofcode.adventofcode2018.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SumOfItsParts}.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsPart1Test extends SolverTest<SumOfItsPartsPart1> {
    /** Constructor. */
    public SumOfItsPartsPart1Test() {
        super(SumOfItsPartsPart1.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        assertSolution("CABDFE", "example-day07-2018.txt");
    }
    
    /** Test case based on the accepted solution for part 1. */
    @Test
    public void testSolution() {
        assertSolution("PFKQWJSVUXEMNIHGTYDOZACRLB", "input-day07-2018.txt");
    }
}
