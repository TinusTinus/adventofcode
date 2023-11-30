package nl.mvdr.adventofcode.adventofcode2018.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalConversionPart2}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // This test case is very slow
public class ChronalConverstionPart2Test extends SolverTest<ChronalConversionPart2> {
    /** Constructor. */
    public ChronalConverstionPart2Test() {
        super(ChronalConversionPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6577657", "input-day21-2018.txt"));
    }
}
