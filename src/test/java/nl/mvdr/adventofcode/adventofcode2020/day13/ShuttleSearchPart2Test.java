package nl.mvdr.adventofcode.adventofcode2020.day13;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ShuttleSearchPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart2Test extends SolverTest<ShuttleSearchPart2> {

    /** Constructor. */
    public ShuttleSearchPart2Test() {
        super(ShuttleSearchPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution(new ShuttleSearchPart2(0L), "1068781", "example-day13-2020-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution(new ShuttleSearchPart2(0L), "3417", "example-day13-2020-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution(new ShuttleSearchPart2(0L), "754018", "example-day13-2020-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution(new ShuttleSearchPart2(0L), "779210", "example-day13-2020-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution(new ShuttleSearchPart2(0L), "1261476", "example-day13-2020-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        assertSolution(new ShuttleSearchPart2(0L), "1202161486", "example-day13-2020-5.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // TODO
    public void testSolution() {
        assertSolution("?", "input-day13-2020.txt");
    }

    /**
     * Test case for {@link ShuttleSearchPart2#isMatchingTimestamp(long, Map)}.
     * 
     * Bus IDs: 7,13,x,x,59,x,31,19.
     */
    @Test
    public void testMatchingTimestamp() {
        @SuppressWarnings("boxing")
        Map<Integer, Integer> busIds = Map.of(0, 7, 1, 13, 4, 59, 6, 31, 7, 19);
        
        boolean result = ShuttleSearchPart2.isMatchingTimestamp(1068781L, busIds);
        
        Assertions.assertTrue(result);
    }

    /**
     * Test case for {@link ShuttleSearchPart2#isMatchingTimestamp(long, Map)}.
     * 
     * Bus IDs: 7,13,x,x,59,x,31,19.
     */
    @Test
    public void testNoMatchingTimestamp() {
        @SuppressWarnings("boxing")
        Map<Integer, Integer> busIds = Map.of(0, 7, 1, 13, 4, 59, 6, 31, 7, 19);
        
        boolean result = ShuttleSearchPart2.isMatchingTimestamp(3L, busIds);
        
        Assertions.assertFalse(result);
    }
}
