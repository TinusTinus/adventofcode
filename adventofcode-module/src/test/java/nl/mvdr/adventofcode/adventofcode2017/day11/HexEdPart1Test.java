package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link HexEdPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart1Test extends SolverTest<HexEdPart1> {

    /** Constructor. */
    public HexEdPart1Test() {
        super(HexEdPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day11-2017-0.txt"),
                Arguments.of("0", "example-day11-2017-1.txt"),
                Arguments.of("2", "example-day11-2017-2.txt"),
                Arguments.of("3", "example-day11-2017-3.txt"));
    }
}
