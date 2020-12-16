package nl.mvdr.adventofcode.adventofcode2020.day10;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AdapterArrayPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AdapterArrayPart2Test extends SolverTest<AdapterArrayPart2> {

    /** Constructor. */
    public AdapterArrayPart2Test() {
        super(AdapterArrayPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * Given the adapters from the first example, the total number of arrangements that connect the charging outlet to your device is 8.
     */
    @Test
    public void testExample0() {
        assertSolution("8", "example-day10-2020-0.txt");
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In total, this set of adapters can connect the charging outlet to your device in 19208 distinct arrangements.
     */
    @Test
    public void testExample1() {
        assertSolution("19208", "example-day10-2020-1.txt");
    }
    
    /** Test case with only one adapter. */
    @Test
    public void testExample2() {
        assertSolution("1", "example-day10-2020-2.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // TODO enable
    public void testSolution() {
        assertSolution("?", "input-day10-2020.txt");
    }
}
