package nl.mvdr.adventofcode.adventofcode2020.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TicketTranslationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TicketTranslationPart1Test extends SolverTest<TicketTranslationPart1> {

    /** Constructor. */
    public TicketTranslationPart1Test() {
        super(TicketTranslationPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("71", "example-day16-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("27870", "input-day16-2020.txt");
    }
}
