package nl.mvdr.adventofcode.adventofcode2018.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalConversionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConverstionPart1Test extends SolverTest<ChronalConversionPart1> {
    /** Constructor. */
    public ChronalConverstionPart1Test() {
        super(ChronalConversionPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                // The first time the instruction on line 30 is executed, the register values are:
                // [<input>, 0, 1, 30, 11840402, 1]
                // So the answer is 11840402.
                Arguments.of("11840402", "input-day21-2018.txt"));
    }
}
