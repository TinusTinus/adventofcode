package nl.mvdr.adventofcode.adventofcode2020.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ShuttleSearchPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart1Test extends SolverTest<ShuttleSearchPart1> {

    /** Constructor. */
    public ShuttleSearchPart1Test() {
        super(ShuttleSearchPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * The earliest bus you could take is bus ID 59. It doesn't depart until
     * timestamp 944, so you would need to wait 944 - 939 = 5 minutes before it
     * departs. Multiplying the bus ID by the number of minutes you'd need to wait
     * gives 295.
     */
    @Test
    public void testExample() {
        assertSolution("295", "example-day13-2020-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("3269", "input-day13-2020.txt");
    }
}
