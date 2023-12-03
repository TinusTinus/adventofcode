package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GearRatiosPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class GearRatiosPart2Test extends SolverTest<GearRatiosPart2> {

    /** Constructor. */
    public GearRatiosPart2Test() {
        super(GearRatiosPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("467835", "example-day03-2023.txt"),
                Arguments.of("?", "input-day03-2023.txt")); // 527438 was too high!
    }
}
