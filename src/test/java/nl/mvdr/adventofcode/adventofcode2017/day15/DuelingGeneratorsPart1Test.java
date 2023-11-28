package nl.mvdr.adventofcode.adventofcode2017.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuelingGeneratorsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DuelingGeneratorsPart1Test extends SolverTest<DuelingGeneratorsPart1> {

    /** Constructor. */
    public DuelingGeneratorsPart1Test() {
        super(DuelingGeneratorsPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("588", "example-day15-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("597", "input-day15-2017.txt");
    }
}
