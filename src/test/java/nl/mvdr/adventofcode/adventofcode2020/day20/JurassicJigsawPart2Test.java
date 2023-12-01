package nl.mvdr.adventofcode.adventofcode2020.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link JurassicJigsawPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart2Test extends SolverTest<JurassicJigsawPart2> {

    /** Constructor. */
    public JurassicJigsawPart2Test() {
        super(JurassicJigsawPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("273", "example-day20-2020.txt"),
                Arguments.of("2065", "input-day20-2020.txt"));
    }
}
