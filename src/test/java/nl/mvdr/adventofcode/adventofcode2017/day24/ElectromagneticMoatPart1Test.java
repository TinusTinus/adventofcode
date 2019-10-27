package nl.mvdr.adventofcode.adventofcode2017.day24;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ElectromagneticMoatPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ElectromagneticMoatPart1Test extends SolverTest<ElectromagneticMoatPart1> {

    /** Constructor. */
    public ElectromagneticMoatPart1Test() {
        super(ElectromagneticMoatPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("31", "example-day24-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1940", "input-day24-2017.txt");
    }
}
