package nl.mvdr.adventofcode.adventofcode2017.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuelingGeneratorsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DuelingGeneratorsPart2Test extends SolverTest<DuelingGeneratorsPart2> {

    /** Constructor. */
    public DuelingGeneratorsPart2Test() {
        super(DuelingGeneratorsPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("309", "example-day15-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("303", "input-day15-2017.txt");
    }
}
