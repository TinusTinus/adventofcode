package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuetPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart1Test extends SolverTest<DuetPart1> {

    /** Constructor. */
    public DuetPart1Test() {
        super(DuetPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day18-2017-0.txt"));
    }
}
