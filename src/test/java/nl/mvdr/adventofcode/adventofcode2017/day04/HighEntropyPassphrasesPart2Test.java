package nl.mvdr.adventofcode.adventofcode2017.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HighEntropyPassphrasesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart2Test extends SolverTest<HighEntropyPassphrasesPart2> {

    /** Constructor. */
    public HighEntropyPassphrasesPart2Test() {
        super(HighEntropyPassphrasesPart2.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("3", "example-day04-2017-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("208", "input-day04-2017.txt");
    }
}
