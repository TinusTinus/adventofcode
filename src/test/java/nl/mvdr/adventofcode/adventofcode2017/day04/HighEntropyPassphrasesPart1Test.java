package nl.mvdr.adventofcode.adventofcode2017.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HighEntropyPassphrasesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart1Test extends SolverTest<HighEntropyPassphrasesPart1> {

    /** Constructor. */
    public HighEntropyPassphrasesPart1Test() {
        super(HighEntropyPassphrasesPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("1", "example-day04-2017-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("0", "example-day04-2017-1.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("1", "example-day04-2017-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("2", "example-day04-2017-3.txt");
    }
    
    /** Test case based on the accepted slution. */
    @Test
    public void testSolution() {
        assertSolution("386", "input-day04-2017.txt");
    }
}
