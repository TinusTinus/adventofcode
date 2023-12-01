package nl.mvdr.adventofcode.adventofcode2022.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TuningTroublePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TuningTroublePart1Test extends SolverTest<TuningTroublePart1> {

    /** Constructor. */
    public TuningTroublePart1Test() {
        super(TuningTroublePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("7", "example-day06-2022-0.txt"),
                Arguments.of("5", "example-day06-2022-1.txt"),
                Arguments.of("6", "example-day06-2022-2.txt"),
                Arguments.of("10", "example-day06-2022-3.txt"),
                Arguments.of("11", "example-day06-2022-4.txt"),
                Arguments.of("1238", "input-day06-2022.txt"));
    }
}
