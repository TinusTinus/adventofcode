package nl.mvdr.adventofcode.adventofcode2022.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CalorieCountingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CalorieCountingPart1Test extends SolverTest<CalorieCountingPart1> {

    /** Constructor. */
    public CalorieCountingPart1Test() {
        super(CalorieCountingPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("24000", "example-day01-2022.txt"));
    }
}
