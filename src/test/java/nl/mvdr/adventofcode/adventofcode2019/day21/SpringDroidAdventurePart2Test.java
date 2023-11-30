package nl.mvdr.adventofcode.adventofcode2019.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpringdroidAdventurePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpringDroidAdventurePart2Test extends SolverTest<SpringdroidAdventurePart2> {

    /** Constructor. */
    public SpringDroidAdventurePart2Test() {
        super(SpringdroidAdventurePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1141869516", "input-day21-2019.txt"));
    }
}
