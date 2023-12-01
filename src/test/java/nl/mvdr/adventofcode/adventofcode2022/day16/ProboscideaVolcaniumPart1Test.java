package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProboscideaVolcaniumPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart1Test extends SolverTest<ProboscideaVolcaniumPart1> {

    /** Constructor. */
    public ProboscideaVolcaniumPart1Test() {
        super(ProboscideaVolcaniumPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1651", "example-day16-2022.txt"),
                Arguments.of("1896", "input-day16-2022.txt"));
    }
}
