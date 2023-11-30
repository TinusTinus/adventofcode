package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OxygenSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class OxygenSystemPart1Test extends SolverTest<OxygenSystemPart1> {

    /** Constructor. */
    public OxygenSystemPart1Test() {
        super(OxygenSystemPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("318", "input-day15-2019.txt"));
    }
}
