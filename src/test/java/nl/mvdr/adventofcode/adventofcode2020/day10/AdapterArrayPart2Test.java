package nl.mvdr.adventofcode.adventofcode2020.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AdapterArrayPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AdapterArrayPart2Test extends SolverTest<AdapterArrayPart2> {

    /** Constructor. */
    public AdapterArrayPart2Test() {
        super(AdapterArrayPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8", "example-day10-2020-0.txt"),
                Arguments.of("19208", "example-day10-2020-1.txt"),
                Arguments.of("1", "example-day10-2020-2.txt"));
    }
}
