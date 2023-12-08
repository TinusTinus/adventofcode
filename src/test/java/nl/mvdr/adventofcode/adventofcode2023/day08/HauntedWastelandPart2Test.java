package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HauntedWastelandPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HauntedWastelandPart2Test extends SolverTest<HauntedWastelandPart2> {

    /** Constructor. */
    public HauntedWastelandPart2Test() {
        super(HauntedWastelandPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day08-2023-0.txt"),
                Arguments.of("6", "example-day08-2023-1.txt"),
                Arguments.of("6", "example-day08-2023-2.txt"),
                Arguments.of("8245452805243", "input-day08-2023.txt"));
    }
}
