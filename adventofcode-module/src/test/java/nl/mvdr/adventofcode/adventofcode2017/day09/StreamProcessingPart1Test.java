package nl.mvdr.adventofcode.adventofcode2017.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link StreamProcessingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class StreamProcessingPart1Test extends SolverTest<StreamProcessingPart1> {

    /** Constructor. */
    public StreamProcessingPart1Test() {
        super(StreamProcessingPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day09-2017-0.txt"),
                Arguments.of("6", "example-day09-2017-1.txt"),
                Arguments.of("5", "example-day09-2017-2.txt"),
                Arguments.of("16", "example-day09-2017-3.txt"),
                Arguments.of("1", "example-day09-2017-4.txt"),
                Arguments.of("9", "example-day09-2017-5.txt"),
                Arguments.of("9", "example-day09-2017-6.txt"),
                Arguments.of("3", "example-day09-2017-7.txt"));
    }
}
