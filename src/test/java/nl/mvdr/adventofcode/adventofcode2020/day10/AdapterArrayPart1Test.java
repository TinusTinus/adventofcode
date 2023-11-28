package nl.mvdr.adventofcode.adventofcode2020.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AdapterArrayPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AdapterArrayPart1Test extends SolverTest<AdapterArrayPart1> {

    /** Constructor. */
    public AdapterArrayPart1Test() {
        super(AdapterArrayPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this example, when using every adapter, there are 7 differences of 1 jolt and 5 differences of 3 jolts.
     */
    @Test
    public void testExample0() {
        testSolution("35", "example-day10-2020-0.txt");
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this larger example, in a chain that uses all of the adapters, there are 22 differences of 1 jolt and 10 differences of 3 jolts.
     */
    @Test
    public void testExample1() {
        testSolution("220", "example-day10-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("2775", "input-day10-2020.txt");
    }
}
