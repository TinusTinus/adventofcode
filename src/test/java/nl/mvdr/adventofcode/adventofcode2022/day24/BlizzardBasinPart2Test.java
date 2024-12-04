package nl.mvdr.adventofcode.adventofcode2022.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BlizzardBasinPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BlizzardBasinPart2Test extends SolverTest<BlizzardBasinPart2> {

    /** Constructor. */
    public BlizzardBasinPart2Test() {
        super(BlizzardBasinPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("30", "example-day24-2022-0.txt"),
                Arguments.of("54", "example-day24-2022-1.txt")); 
    }
}
