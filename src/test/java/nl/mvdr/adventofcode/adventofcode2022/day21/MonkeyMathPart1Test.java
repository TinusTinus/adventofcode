package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMathPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart1Test extends SolverTest<MonkeyMathPart1> {

    /** Constructor. */
    public MonkeyMathPart1Test() {
        super(MonkeyMathPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("152", "example-day21-2022.txt"),
                Arguments.of("256997859093114", "input-day21-2022.txt"));
    }
}
