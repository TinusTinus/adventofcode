package nl.mvdr.adventofcode.adventofcode2020.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link JurassicJigsawPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart1Test extends SolverTest<JurassicJigsawPart1> {

    /** Constructor. */
    public JurassicJigsawPart1Test() {
        super(JurassicJigsawPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("20899048083289", "example-day20-2020.txt"));
    }
}
