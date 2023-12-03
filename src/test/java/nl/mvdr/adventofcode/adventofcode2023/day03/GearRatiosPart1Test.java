package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link GearRatiosPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class GearRatiosPart1Test extends SolverTest<GearRatiosPart1> {

    /** Constructor. */
    public GearRatiosPart1Test() {
        super(GearRatiosPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4361", "example-day03-2023.txt"),
                Arguments.of("521515", "input-day03-2023.txt")); // 527438 was too high!
    }
}
