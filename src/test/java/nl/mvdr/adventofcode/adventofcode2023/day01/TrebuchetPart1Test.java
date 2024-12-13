package nl.mvdr.adventofcode.adventofcode2023.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link TrebuchetPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TrebuchetPart1Test extends SolverTest<TrebuchetPart1> {

    /** Constructor. */
    public TrebuchetPart1Test() {
        super(TrebuchetPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("142", "example-day01-2023-part1.txt"));
    }
}
