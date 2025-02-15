package nl.mvdr.adventofcode.adventofcode2016.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link IPv7Part2}.
 *
 * @author Martijn van de Rijdt
 */
public class IPv7Part2Test extends SolverTest<IPv7Part2> {

    /** Constructor. */
    public IPv7Part2Test() {
        super(IPv7Part2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day07-2016-1.txt"));
    }
}
