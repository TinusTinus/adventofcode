package nl.mvdr.adventofcode.adventofcode2022.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RucksackReorganizationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RucksackReorganizationPart1Test extends SolverTest<RucksackReorganizationPart1> {

    /** Constructor. */
    public RucksackReorganizationPart1Test() {
        super(RucksackReorganizationPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("157", "example-day03-2022.txt"));
    }
}
