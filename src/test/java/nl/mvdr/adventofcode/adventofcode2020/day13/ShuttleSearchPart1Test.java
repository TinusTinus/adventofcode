package nl.mvdr.adventofcode.adventofcode2020.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ShuttleSearchPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ShuttleSearchPart1Test extends SolverTest<ShuttleSearchPart1> {

    /** Constructor. */
    public ShuttleSearchPart1Test() {
        super(ShuttleSearchPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("295", "example-day13-2020-0.txt"));
    }
}
