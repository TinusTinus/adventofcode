package nl.mvdr.adventofcode.adventofcode2017.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SeriesOfTubesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart1Test extends SolverTest<SeriesOfTubesPart1> {

    /** Constructor. */
    public SeriesOfTubesPart1Test() {
        super(SeriesOfTubesPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("ABCDEF", "example-day19-2017.txt"));
    }
}
