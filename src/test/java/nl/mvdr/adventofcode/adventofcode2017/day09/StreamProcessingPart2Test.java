package nl.mvdr.adventofcode.adventofcode2017.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link StreamProcessingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class StreamProcessingPart2Test extends SolverTest<StreamProcessingPart2> {

    /** Constructor. */
    public StreamProcessingPart2Test() {
        super(StreamProcessingPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5601", "input-day09-2017.txt"));
    }
}
