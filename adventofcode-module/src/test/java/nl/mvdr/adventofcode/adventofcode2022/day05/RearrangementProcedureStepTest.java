package nl.mvdr.adventofcode.adventofcode2022.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Step}.
 *
 * @author Martijn van de Rijdt
 */
public class RearrangementProcedureStepTest {
    
    /**
     * Test case for {@link Step#parse(String)}.
     */
    @Test
    public void testParse() {
        String input = "move 3 from 1 to 3";
        
        Step step = Step.parse(input);
        
        Assertions.assertEquals(3, step.numberOfCrates());
        Assertions.assertEquals(1, step.sourceStack());
        Assertions.assertEquals(3, step.targetStack());
    }
    
    /**
     * Test case for {@link Step#parse(String)}.
     */
    @Test
    public void testParseMultipleDigits() {
        String input = "move 15 from 6 to 4";
        
        Step step = Step.parse(input);
        
        Assertions.assertEquals(15, step.numberOfCrates());
        Assertions.assertEquals(6, step.sourceStack());
        Assertions.assertEquals(4, step.targetStack());
    }
}
