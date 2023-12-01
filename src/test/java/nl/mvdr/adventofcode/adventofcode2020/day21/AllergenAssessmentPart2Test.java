package nl.mvdr.adventofcode.adventofcode2020.day21;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AllergenAssessmentPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class AllergenAssessmentPart2Test extends SolverTest<AllergenAssessmentPart2> {

    /** Constructor. */
    public AllergenAssessmentPart2Test() {
        super(AllergenAssessmentPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("mxmxvkd,sqjhc,fvjkl", "example-day21-2020.txt"),
                Arguments.of("vpzxk,bkgmcsx,qfzv,tjtgbf,rjdqt,hbnf,jspkl,hdcj", "input-day21-2020.txt"));
    }
}
