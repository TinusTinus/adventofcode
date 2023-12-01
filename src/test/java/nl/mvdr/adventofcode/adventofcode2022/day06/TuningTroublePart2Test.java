package nl.mvdr.adventofcode.adventofcode2022.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TuningTroublePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TuningTroublePart2Test extends SolverTest<TuningTroublePart2> {

    /** Constructor. */
    public TuningTroublePart2Test() {
        super(TuningTroublePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("19", "example-day06-2022-0.txt"),
                Arguments.of("23", "example-day06-2022-1.txt"),
                Arguments.of("23", "example-day06-2022-2.txt"),
                Arguments.of("29", "example-day06-2022-3.txt"),
                Arguments.of("26", "example-day06-2022-4.txt"),
                Arguments.of("3037", "input-day06-2022.txt"));
    }
}
