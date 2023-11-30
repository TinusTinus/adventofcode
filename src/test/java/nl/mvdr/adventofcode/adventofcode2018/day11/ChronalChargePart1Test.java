package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalChargePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalChargePart1Test extends SolverTest<ChronalChargePart1> {
    /** Constructor. */
    public ChronalChargePart1Test() {
        super(ChronalChargePart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("33,45", "example-day11-2018-0.txt"),
                Arguments.of("21,61", "example-day11-2018-1.txt"),
                Arguments.of("216,12", "input-day11-2018.txt"));
    }
}
