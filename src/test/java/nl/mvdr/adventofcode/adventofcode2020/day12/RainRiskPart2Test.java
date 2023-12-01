package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RainRiskPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RainRiskPart2Test extends SolverTest<RainRiskPart2> {

    /** Constructor. */
    public RainRiskPart2Test() {
        super(RainRiskPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("286", "example-day12-2020.txt"),
                Arguments.of("78883", "input-day12-2020.txt"));
    }
}
