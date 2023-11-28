package nl.mvdr.adventofcode.adventofcode2016.day03;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SquaresPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart2Test extends SolverTest<SquaresPart2> {

    /** Constructor. */
    public SquaresPart2Test() {
        super(SquaresPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1921", "input-day03-2016.txt"));
    }
}
