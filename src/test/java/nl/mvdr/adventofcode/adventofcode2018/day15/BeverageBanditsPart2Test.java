package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link BeverageBanditsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BeverageBanditsPart2Test extends SolverTest<BeverageBanditsPart2> {
    /** Constructor. */
    public BeverageBanditsPart2Test() {
        super(BeverageBanditsPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4988", "example-day15-2018-0.txt"),
                Arguments.of("31284", "example-day15-2018-2.txt"),
                Arguments.of("3478", "example-day15-2018-3.txt"),
                Arguments.of("6474", "example-day15-2018-4.txt"),
                Arguments.of("1140", "example-day15-2018-5.txt"));
    }
}
