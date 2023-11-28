package nl.mvdr.adventofcode.adventofcode2022.day06;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TuningTroublePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TuningTroublePart1Test extends SolverTest<TuningTroublePart1> {

    /** Constructor. */
    public TuningTroublePart1Test() {
        super(TuningTroublePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("7", "example-day06-2022-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("5", "example-day06-2022-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("6", "example-day06-2022-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("10", "example-day06-2022-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("11", "example-day06-2022-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1238", "input-day06-2022.txt");
    }
}
