package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpinlockPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart2Test extends SolverTest<SpinlockPart2> {

    /** Constructor. */
    public SpinlockPart2Test() {
        super(SpinlockPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("27361412", "input-day17-2017.txt"));
    }
}
