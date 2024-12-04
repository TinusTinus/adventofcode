package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpinlockPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpinlockPart1Test extends SolverTest<SpinlockPart1> {

    /** Constructor. */
    public SpinlockPart1Test() {
        super(SpinlockPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("638", "example-day17-2017.txt"));
    }
}
