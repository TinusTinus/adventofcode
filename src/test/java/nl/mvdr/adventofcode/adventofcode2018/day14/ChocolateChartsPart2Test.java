package nl.mvdr.adventofcode.adventofcode2018.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChocolateChartsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChocolateChartsPart2Test extends SolverTest<ChocolateChartsPart2> {
    /** Constructor. */
    public ChocolateChartsPart2Test() {
        super(ChocolateChartsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9", "example-day14-2018-6.txt"),
                Arguments.of("5", "example-day14-2018-7.txt"),
                Arguments.of("18", "example-day14-2018-8.txt"),
                Arguments.of("2018", "example-day14-2018-9.txt"),
                Arguments.of("20278122", "input-day14-2018.txt"));
    }
}
