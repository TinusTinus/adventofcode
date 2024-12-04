package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AllergenAssessmentPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class AllergenAssessmentPart1Test extends SolverTest<AllergenAssessmentPart1> {

    /** Constructor. */
    public AllergenAssessmentPart1Test() {
        super(AllergenAssessmentPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day21-2020.txt"));
    }
}
