package nl.mvdr.adventofcode.adventofcode2023.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TrebuchetPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TrebuchetPart2Test extends SolverTest<TrebuchetPart2> {

    /** Constructor. */
    public TrebuchetPart2Test() {
        super(TrebuchetPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("142", "example-day01-2023-part1.txt"),
                Arguments.of("281", "example-day01-2023-part2.txt"));
    }
}
