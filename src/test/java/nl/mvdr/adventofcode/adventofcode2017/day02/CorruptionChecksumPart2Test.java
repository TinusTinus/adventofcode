package nl.mvdr.adventofcode.adventofcode2017.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CorruptionChecksumPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CorruptionChecksumPart2Test extends SolverTest<CorruptionChecksumPart2> {

    /** Constructor. */
    public CorruptionChecksumPart2Test() {
        super(CorruptionChecksumPart2.class);
    }
    

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9", "example-day02-2017-1.txt"),
                Arguments.of("265", "input-day02-2017.txt"));
    }
}
