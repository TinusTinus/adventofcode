package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PointOfIncidencePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PointOfIncidencePart1Test extends SolverTest<PointOfIncidencePart1> {

    /** Constructor. */
    public PointOfIncidencePart1Test() {
        super(PointOfIncidencePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("405", "example-day13-2023.txt"),
                Arguments.of("35521", "input-day13-2023.txt"));
    }
}
