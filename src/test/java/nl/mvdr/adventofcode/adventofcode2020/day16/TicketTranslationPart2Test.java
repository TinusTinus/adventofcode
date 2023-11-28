package nl.mvdr.adventofcode.adventofcode2020.day16;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TicketTranslationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TicketTranslationPart2Test extends SolverTest<TicketTranslationPart2> {

    /** Constructor. */
    public TicketTranslationPart2Test() {
        super(TicketTranslationPart2.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("3173135507987", "input-day16-2020.txt");
    }
}
