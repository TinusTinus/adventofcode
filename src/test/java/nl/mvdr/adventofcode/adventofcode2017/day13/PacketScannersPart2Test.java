package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

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
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                // Because all smaller delays would get you caught,
                // the fewest number of picoseconds you would need to delay to get through safely is 10.
                // Note: day 4 has severity = 0, but the packet does get caught, at layer 0.
                Arguments.of("10", "example-day13-2017.txt"));
    }
}
