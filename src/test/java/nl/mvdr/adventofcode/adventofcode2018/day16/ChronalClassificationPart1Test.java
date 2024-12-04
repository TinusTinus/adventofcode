package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalClassificationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart1Test extends SolverTest<ChronalClassificationPart1> {
    /** Constructor. */
    public ChronalClassificationPart1Test() {
        super(ChronalClassificationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day16-2018.txt"));
    }
}
