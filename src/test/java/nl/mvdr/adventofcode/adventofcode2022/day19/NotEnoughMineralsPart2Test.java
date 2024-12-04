package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NotEnoughMineralsPart2}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // Takes a long time to run
public class NotEnoughMineralsPart2Test extends SolverTest<NotEnoughMineralsPart2> {

    /** Constructor. */
    public NotEnoughMineralsPart2Test() {
        super(NotEnoughMineralsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3472", "example-day19-2022.txt")); // 62 * 56
    }
}
