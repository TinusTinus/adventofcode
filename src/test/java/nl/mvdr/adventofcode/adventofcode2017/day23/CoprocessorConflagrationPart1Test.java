package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CoprocessorConflagrationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart1Test extends SolverTest<CoprocessorConflagrationPart1> {

    /** Constructor. */
    public CoprocessorConflagrationPart1Test() {
        super(CoprocessorConflagrationPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3969", "input-day23-2017.txt"));
    }
}
