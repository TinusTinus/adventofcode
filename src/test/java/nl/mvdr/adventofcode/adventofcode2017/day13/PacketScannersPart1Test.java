package nl.mvdr.adventofcode.adventofcode2017.day13;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PacketScannersPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PacketScannersPart1Test extends SolverTest<PacketScannersPart1> {

    /** Constructor. */
    public PacketScannersPart1Test() {
        super(PacketScannersPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        // In the example, the trip severity is 0*3 + 6*4 = 24
        testSolution("24", "example-day13-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1844", "input-day13-2017.txt");
    }
}
