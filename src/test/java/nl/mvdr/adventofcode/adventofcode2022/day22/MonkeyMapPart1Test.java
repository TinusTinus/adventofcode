package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMapPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart1Test extends SolverTest<MonkeyMapPart1> {

    /** Constructor. */
    public MonkeyMapPart1Test() {
        super(MonkeyMapPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6032", "example-day22-2022.txt"),
                Arguments.of("123046", "input-day22-2022.txt"));
    }
}
