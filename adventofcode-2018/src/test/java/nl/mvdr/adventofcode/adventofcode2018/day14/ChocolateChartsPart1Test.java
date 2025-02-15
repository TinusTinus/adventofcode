package nl.mvdr.adventofcode.adventofcode2018.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link ChocolateChartsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateChartsPart1Test extends SolverTest<ChocolateChartsPart1> {
    /** Constructor. */
    public ChocolateChartsPart1Test() {
        super(ChocolateChartsPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5158916779", "example-day14-2018-0.txt"),
                Arguments.of("0124515891", "example-day14-2018-1.txt"),
                Arguments.of("9251071085", "example-day14-2018-2.txt"),
                Arguments.of("5941429882", "example-day14-2018-3.txt"),
                Arguments.of("1589167792", "example-day14-2018-4.txt"),
                Arguments.of("3710101245", "example-day14-2018-5.txt"));
    }
}
