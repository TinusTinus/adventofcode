package nl.mvdr.adventofcode.adventofcode2019.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FlawedFrequencyTransmissionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class FlawedFrequencyTransmissionPart2Test extends SolverTest<FlawedFrequencyTransmissionPart2> {

    /** Constructor. */
    public FlawedFrequencyTransmissionPart2Test() {
        super(FlawedFrequencyTransmissionPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("84462026", "example-day16-2019-4.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution("78725270", "example-day16-2019-5.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6() {
        assertSolution("53553731", "example-day16-2019-6.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("85600369", "input-day16-2019.txt"); 
    }
}
