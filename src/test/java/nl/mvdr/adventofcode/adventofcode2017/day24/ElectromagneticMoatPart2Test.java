package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ElectromagneticMoatPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ElectromagneticMoatPart2Test extends SolverTest<ElectromagneticMoatPart2> {

    /** Constructor. */
    public ElectromagneticMoatPart2Test() {
        super(ElectromagneticMoatPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("19", "example-day24-2017.txt"),
                Arguments.of("1928", "input-day24-2017.txt"));
    }
}
