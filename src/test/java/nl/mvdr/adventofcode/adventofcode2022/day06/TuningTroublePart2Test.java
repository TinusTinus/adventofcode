package nl.mvdr.adventofcode.adventofcode2022.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TuningTroublePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TuningTroublePart2Test extends SolverTest<TuningTroublePart2> {

    /** Constructor. */
    public TuningTroublePart2Test() {
        super(TuningTroublePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("19", "example-day06-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("23", "example-day06-2022-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution("23", "example-day06-2022-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution("29", "example-day06-2022-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution("26", "example-day06-2022-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("3037", "input-day06-2022.txt");
    }
}
