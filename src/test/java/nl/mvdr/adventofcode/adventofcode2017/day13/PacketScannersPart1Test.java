package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

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
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("24", "example-day13-2017.txt"));
    }
}
