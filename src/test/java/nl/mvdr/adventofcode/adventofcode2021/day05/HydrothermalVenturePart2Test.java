package nl.mvdr.adventofcode.adventofcode2021.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HydrothermalVenturePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HydrothermalVenturePart2Test extends SolverTest<HydrothermalVenturePart2> {

    /** Constructor. */
    public HydrothermalVenturePart2Test() {
        super(HydrothermalVenturePart2.class);
    }
    
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("12", "example-day05-2021.txt"));
    }
}
