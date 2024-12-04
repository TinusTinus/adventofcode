package nl.mvdr.adventofcode.adventofcode2020.day22;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrabCombatPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCombatPart2Test extends SolverTest<CrabCombatPart2> {

    /** Constructor. */
    public CrabCombatPart2Test() {
        super(CrabCombatPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("291", "example-day22-2020-0.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExampleInfiniteRecursion() {
        int expectedScore = 2 * 43 + 19;
        testSolution(Integer.toString(expectedScore), "example-day22-2020-1.txt");
    }
}
