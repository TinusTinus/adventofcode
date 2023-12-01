package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMathPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart2Test extends SolverTest<MonkeyMathPart2> {

    /** Constructor. */
    public MonkeyMathPart2Test() {
        super(MonkeyMathPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("301", "example-day21-2022.txt"),
                Arguments.of("3952288690726", "input-day21-2022.txt"));
    }
}
