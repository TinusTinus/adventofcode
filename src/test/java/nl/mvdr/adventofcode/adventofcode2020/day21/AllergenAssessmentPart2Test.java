package nl.mvdr.adventofcode.adventofcode2020.day21;

import org.junit.jupiter.api.Test;

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
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("mxmxvkd,sqjhc,fvjkl", "example-day21-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("vpzxk,bkgmcsx,qfzv,tjtgbf,rjdqt,hbnf,jspkl,hdcj", "input-day21-2020.txt");
    }
}
