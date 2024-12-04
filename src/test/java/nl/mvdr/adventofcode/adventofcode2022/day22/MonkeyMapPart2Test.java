package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMapPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMapPart2Test extends SolverTest<MonkeyMapPart2> {

    /** Constructor. */
    public MonkeyMapPart2Test() {
        super(MonkeyMapPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5031", "example-day22-2022.txt"));
    }
}
