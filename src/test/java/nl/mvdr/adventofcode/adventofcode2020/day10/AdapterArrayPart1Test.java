package nl.mvdr.adventofcode.adventofcode2020.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AdapterArrayPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AdapterArrayPart1Test extends SolverTest<AdapterArrayPart1> {

    /** Constructor. */
    public AdapterArrayPart1Test() {
        super(AdapterArrayPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("35", "example-day10-2020-0.txt"),
                Arguments.of("220", "example-day10-2020-1.txt"));
    }
}
