package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyInTheMiddlePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyInTheMiddlePart2Test extends SolverTest<MonkeyInTheMiddlePart2> {

    /** Constructor. */
    public MonkeyInTheMiddlePart2Test() {
        super(MonkeyInTheMiddlePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2713310158", "example-day11-2022.txt"),
                Arguments.of("14399640002", "input-day11-2022.txt"));
    }
}
