package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RainRiskPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RainRiskPart1Test extends SolverTest<RainRiskPart1> {

    /** Constructor. */
    public RainRiskPart1Test() {
        super(RainRiskPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("25", "example-day12-2020.txt"));
    }
}
