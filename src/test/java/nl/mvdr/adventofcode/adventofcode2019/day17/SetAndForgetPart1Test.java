package nl.mvdr.adventofcode.adventofcode2019.day17;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SetAndForgetPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart1Test extends SolverTest<SetAndForgetPart1> {

    /** Constructor. */
    public SetAndForgetPart1Test() {
        super(SetAndForgetPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5724", "input-day17-2019.txt"));
    }
}
