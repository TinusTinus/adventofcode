package nl.mvdr.adventofcode.adventofcode2020.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SeatingSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SeatingSystemPart1Test extends SolverTest<SeatingSystemPart1> {

    /** Constructor. */
    public SeatingSystemPart1Test() {
        super(SeatingSystemPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("37", "example-day11-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("2243", "input-day11-2020.txt");
    }
}
