package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CoprocessorConflagrationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart2Test extends SolverTest<CoprocessorConflagrationPart2> {

    /** Constructor. */
    public CoprocessorConflagrationPart2Test() {
        super(CoprocessorConflagrationPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("917", "input-day23-2017.txt"));
    }
}
