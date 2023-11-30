package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SlamShufflePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SlamShufflePart1Test extends SolverTest<SlamShufflePart1> {

    /** Constructor. */
    public SlamShufflePart1Test() {
        super(SlamShufflePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3324", "input-day22-2019.txt"));
    }
}
