package nl.mvdr.adventofcode.adventofcode2020.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RambunctiousRecitationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RambunctiousRecitationPart2Test extends SolverTest<RambunctiousRecitationPart2> {

    /** Constructor. */
    public RambunctiousRecitationPart2Test() {
        super(RambunctiousRecitationPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("243", "input-day15-2020.txt"));
    }
}
