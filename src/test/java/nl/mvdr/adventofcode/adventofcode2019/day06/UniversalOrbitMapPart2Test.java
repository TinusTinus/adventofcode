package nl.mvdr.adventofcode.adventofcode2019.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link UniversalOrbitMapPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class UniversalOrbitMapPart2Test extends SolverTest<UniversalOrbitMapPart2> {

    /** Constructor. */
    public UniversalOrbitMapPart2Test() {
        super(UniversalOrbitMapPart2.class);
    }
    
    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("4", "example-day06-2019-1.txt"); 
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("316", "input-day06-2019.txt"); 
    }
}
