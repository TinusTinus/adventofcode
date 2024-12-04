package nl.mvdr.adventofcode.adventofcode2023.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HauntedWastelandPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HauntedWastelandPart1Test extends SolverTest<HauntedWastelandPart1> {

    /** Constructor. */
    public HauntedWastelandPart1Test() {
        super(HauntedWastelandPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day08-2023-0.txt"),
                Arguments.of("6", "example-day08-2023-1.txt"));
    }
}
