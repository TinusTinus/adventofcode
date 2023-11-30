package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuetPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart2Test extends SolverTest<DuetPart2> {

    /** Constructor. */
    public DuetPart2Test() {
        super(DuetPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day18-2017-1.txt"),
                Arguments.of("6858", "input-day18-2017.txt"));
    }
}
