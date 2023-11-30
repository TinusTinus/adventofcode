package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalChargePart2}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // These test cases take a long time
public class ChronalChargePart2Test extends SolverTest<ChronalChargePart2> {
    /** Constructor. */
    public ChronalChargePart2Test() {
        super(ChronalChargePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("90,269,16", "example-day11-2018-0.txt"),
                Arguments.of("232,251,12", "example-day11-2018-1.txt"),
                Arguments.of("236,175,11", "input-day11-2018.txt"));
    }
}
