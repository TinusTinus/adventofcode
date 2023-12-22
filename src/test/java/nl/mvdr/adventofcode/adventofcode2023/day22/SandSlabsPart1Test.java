package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SandSlabsPart1}.
 *
 * @author Martijn van de Rijdt
 */
@Disabled // TODO re-enable
public class SandSlabsPart1Test extends SolverTest<SandSlabsPart1> {

    /** Constructor. */
    public SandSlabsPart1Test() {
        super(SandSlabsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day22-2023.txt"),
                Arguments.of("?", "input-day22-2023.txt"));
    }
}
