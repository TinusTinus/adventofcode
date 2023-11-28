package nl.mvdr.adventofcode.adventofcode2020.day21;

import org.junit.jupiter.api.Test;

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
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("5", "example-day21-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("2150", "input-day21-2020.txt");
    }
}
