package nl.mvdr.adventofcode.adventofcode2020.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ShuttleSearchPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart2Test extends SolverTest<ShuttleSearchPart2> {

    /** Constructor. */
    public ShuttleSearchPart2Test() {
        super(ShuttleSearchPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1068781", "example-day13-2020-0.txt"),
                Arguments.of("3417", "example-day13-2020-1.txt"),
                Arguments.of("754018", "example-day13-2020-2.txt"),
                Arguments.of("779210", "example-day13-2020-3.txt"),
                Arguments.of("1261476", "example-day13-2020-4.txt"),
                Arguments.of("1202161486", "example-day13-2020-5.txt"));
    }
}
