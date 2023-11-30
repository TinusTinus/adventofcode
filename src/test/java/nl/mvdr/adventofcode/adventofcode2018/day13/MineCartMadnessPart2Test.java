package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MineCartMadnessPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart2Test extends SolverTest<MineCartMadnessPart2> {
    /** Constructor. */
    public MineCartMadnessPart2Test() {
        super(MineCartMadnessPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6,4", "example-day13-2018-4.txt"),
                Arguments.of("143,123", "input-day13-2018.txt"));
    }
}
