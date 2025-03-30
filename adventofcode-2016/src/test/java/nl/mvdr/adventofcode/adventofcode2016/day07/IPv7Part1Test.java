package nl.mvdr.adventofcode.adventofcode2016.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link IPv7Part1}.
 *
 * @author Martijn van de Rijdt
 */
public class IPv7Part1Test extends SolverTest<IPv7Part1> {

    /** Constructor. */
    public IPv7Part1Test() {
        super(IPv7Part1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day07-0.txt"));
    }
}
