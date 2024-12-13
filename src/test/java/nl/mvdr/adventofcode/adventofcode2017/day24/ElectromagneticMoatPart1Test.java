package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ElectromagneticMoatPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ElectromagneticMoatPart1Test extends SolverTest<ElectromagneticMoatPart1> {

    /** Constructor. */
    public ElectromagneticMoatPart1Test() {
        super(ElectromagneticMoatPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("31", "example-day24-2017.txt"));
    }
}
