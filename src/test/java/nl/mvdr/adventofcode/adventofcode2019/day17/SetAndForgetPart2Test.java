package nl.mvdr.adventofcode.adventofcode2019.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SetAndForgetPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart2Test extends SolverTest<SetAndForgetPart2> {

    /** Constructor. */
    public SetAndForgetPart2Test() {
        super(SetAndForgetPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("732985", "input-day17-2019.txt"));
    }
}
