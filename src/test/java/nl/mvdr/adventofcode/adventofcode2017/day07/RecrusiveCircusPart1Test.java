package nl.mvdr.adventofcode.adventofcode2017.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RecursiveCircusPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RecrusiveCircusPart1Test extends SolverTest<RecursiveCircusPart1> {

    /** Constructor. */
    public RecrusiveCircusPart1Test() {
        super(RecursiveCircusPart1.class);
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("tknk", "example-day07-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("vvsvez", "input-day07-2017.txt");
    }
}
