package nl.mvdr.adventofcode.adventofcode2018.day25;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link FourDimensionalAdventure}.
 *
 * @author Martijn van de Rijdt
 */
public class FourDimensionalAdventureTest extends SolverTest<FourDimensionalAdventure> {
    /** Constructor. */
    public FourDimensionalAdventureTest() {
        super(FourDimensionalAdventure.class);
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample0() {
        assertSolution("2", "example-day25-2018-0.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        assertSolution("1", "example-day25-2018-1.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample2() {
        assertSolution("4", "example-day25-2018-2.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        assertSolution("3", "example-day25-2018-3.txt");
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample4() {
        assertSolution("8", "example-day25-2018-4.txt");
    }
    
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        assertSolution("396", "input-day25-2018.txt");
    }
}
