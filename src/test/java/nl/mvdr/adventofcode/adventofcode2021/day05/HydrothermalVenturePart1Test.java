package nl.mvdr.adventofcode.adventofcode2021.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HydrothermalVenturePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HydrothermalVenturePart1Test extends SolverTest<HydrothermalVenturePart1> {

    /** Constructor. */
    public HydrothermalVenturePart1Test() {
        super(HydrothermalVenturePart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5",    "example-day05-2021.txt"));
    }
}
