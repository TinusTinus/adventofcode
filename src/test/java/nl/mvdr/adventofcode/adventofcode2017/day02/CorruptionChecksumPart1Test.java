package nl.mvdr.adventofcode.adventofcode2017.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CorruptionChecksumPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart1Test extends SolverTest<CorruptionChecksumPart1> {

    /** Constructor. */
    public CorruptionChecksumPart1Test() {
        super(CorruptionChecksumPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("18", "example-day02-2017-0.txt"));
    }
}
