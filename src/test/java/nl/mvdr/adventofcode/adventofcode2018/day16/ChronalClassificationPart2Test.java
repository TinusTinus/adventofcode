package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link ChronalClassificationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart2Test extends SolverTest<ChronalClassificationPart2> {
    /** Constructor. */
    public ChronalClassificationPart2Test() {
        super(ChronalClassificationPart2.class);
    }
    
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("681", "input-day16-2018.txt"));
    }
}
