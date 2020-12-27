package nl.mvdr.adventofcode.adventofcode2020.day15;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RambunctiousRecitationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RambunctiousRecitationPart2Test extends SolverTest<RambunctiousRecitationPart2> {

    /** Constructor. */
    public RambunctiousRecitationPart2Test() {
        super(RambunctiousRecitationPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("243", "input-day15-2020.txt");
    }
}
