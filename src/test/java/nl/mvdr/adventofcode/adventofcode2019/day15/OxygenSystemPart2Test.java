package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OxygenSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class OxygenSystemPart2Test extends SolverTest<OxygenSystemPart2> {

    /** Constructor. */
    public OxygenSystemPart2Test() {
        super(OxygenSystemPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("390", "input-day15-2019.txt"));
    }
}
