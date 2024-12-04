package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PointOfIncidencePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PointOfIncidencePart2Test extends SolverTest<PointOfIncidencePart2> {

    /** Constructor. */
    public PointOfIncidencePart2Test() {
        super(PointOfIncidencePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("400", "example-day13-2023.txt"));
    }
}
