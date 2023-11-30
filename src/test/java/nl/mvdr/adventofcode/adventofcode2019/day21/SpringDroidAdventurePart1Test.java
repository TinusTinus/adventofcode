package nl.mvdr.adventofcode.adventofcode2019.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpringdroidAdventurePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpringDroidAdventurePart1Test extends SolverTest<SpringdroidAdventurePart1> {

    /** Constructor. */
    public SpringDroidAdventurePart1Test() {
        super(SpringdroidAdventurePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("19359752", "input-day21-2019.txt"));
    }
}
