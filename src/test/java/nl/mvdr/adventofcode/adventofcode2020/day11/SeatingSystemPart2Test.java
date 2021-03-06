package nl.mvdr.adventofcode.adventofcode2020.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SeatingSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SeatingSystemPart2Test extends SolverTest<SeatingSystemPart2> {

    /** Constructor. */
    public SeatingSystemPart2Test() {
        super(SeatingSystemPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("26", "example-day11-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("2027", "input-day11-2020.txt");
    }
}
