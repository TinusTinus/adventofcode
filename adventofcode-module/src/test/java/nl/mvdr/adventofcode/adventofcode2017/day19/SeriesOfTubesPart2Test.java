package nl.mvdr.adventofcode.adventofcode2017.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link SeriesOfTubesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart2Test extends SolverTest<SeriesOfTubesPart2> {

    /** Constructor. */
    public SeriesOfTubesPart2Test() {
        super(SeriesOfTubesPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("38", "example-day19-2017.txt"));
    }
}
