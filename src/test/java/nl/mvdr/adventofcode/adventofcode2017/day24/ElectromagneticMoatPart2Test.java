package nl.mvdr.adventofcode.adventofcode2017.day24;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ElectromagneticMoatPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ElectromagneticMoatPart2Test extends SolverTest<ElectromagneticMoatPart2> {

    /** Constructor. */
    public ElectromagneticMoatPart2Test() {
        super(ElectromagneticMoatPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("19", "example-day24-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1928", "input-day24-2017.txt");
    }
}
