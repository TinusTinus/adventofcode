package nl.mvdr.adventofcode.adventofcode2020.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link SeatingSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SeatingSystemPart1Test extends SolverTest<SeatingSystemPart1> {

    /** Constructor. */
    public SeatingSystemPart1Test() {
        super(SeatingSystemPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("37", "example-day11-2020.txt"));
    }
}
