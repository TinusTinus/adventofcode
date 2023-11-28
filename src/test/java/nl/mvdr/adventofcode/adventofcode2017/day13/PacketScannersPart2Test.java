package nl.mvdr.adventofcode.adventofcode2017.day13;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PacketScannersPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PacketScannersPart2Test extends SolverTest<PacketScannersPart2> {

    /** Constructor. */
    public PacketScannersPart2Test() {
        super(PacketScannersPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        // Because all smaller delays would get you caught,
        // the fewest number of picoseconds you would need to delay to get through safely is 10.
        // Note: day 4 has severity = 0, but the packet does get caught, at layer 0.
        testSolution("10", "example-day13-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    @Disabled // disabled by default, as this test case typically takes over two minutes to complete
    public void testSolution() {
        testSolution("3897604", "input-day13-2017.txt");
    }
}
