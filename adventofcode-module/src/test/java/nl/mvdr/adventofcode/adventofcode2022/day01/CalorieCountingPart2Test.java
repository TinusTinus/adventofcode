package nl.mvdr.adventofcode.adventofcode2022.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link CalorieCountingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CalorieCountingPart2Test extends SolverTest<CalorieCountingPart2> {

    /** Constructor. */
    public CalorieCountingPart2Test() {
        super(CalorieCountingPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("45000", "example-day01-2022.txt"));
    }
}
