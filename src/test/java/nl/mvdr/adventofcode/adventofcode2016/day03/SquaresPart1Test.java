package nl.mvdr.adventofcode.adventofcode2016.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SquaresPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart1Test extends SolverTest<SquaresPart1> {

    /** Constructor. */
    public SquaresPart1Test() {
        super(SquaresPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day03-2016.txt"),
                Arguments.of("1050", "input-day03-2016.txt"));
    }
}
