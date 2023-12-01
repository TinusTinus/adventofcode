package nl.mvdr.adventofcode.adventofcode2022.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RucksackReorganizationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RucksackReorganizationPart2Test extends SolverTest<RucksackReorganizationPart2> {

    /** Constructor. */
    public RucksackReorganizationPart2Test() {
        super(RucksackReorganizationPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("70", "example-day03-2022.txt"),
                Arguments.of("2581", "input-day03-2022.txt"));
    }
}
