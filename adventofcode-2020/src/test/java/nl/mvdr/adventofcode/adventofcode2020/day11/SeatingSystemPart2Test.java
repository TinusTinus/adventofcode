package nl.mvdr.adventofcode.adventofcode2020.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link SeatingSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SeatingSystemPart2Test extends SolverTest<SeatingSystemPart2> {

    /** Constructor. */
    public SeatingSystemPart2Test() {
        super(SeatingSystemPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("26", "example-day11-2020.txt"));
    }
}
