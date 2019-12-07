package nl.mvdr.adventofcode.adventofcode2019.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link UniversalOrbitMapPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class UniversalOrbitMapPart1Test extends SolverTest<UniversalOrbitMapPart1> {

    /** Constructor. */
    public UniversalOrbitMapPart1Test() {
        super(UniversalOrbitMapPart1.class);
    }
    
    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("42", "example-day06-2019.txt"); 
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("194721", "input-day06-2019.txt"); 
    }
}
