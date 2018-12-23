package nl.mvdr.adventofcode.adventofcode2018.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Claim}.
 *
 * @author Martijn van de Rijdt
 */
public class ClaimTest {
    /** Test case for {@link Claim#parse(String)} and {@link Claim#toString()}. */
    @Test
    public void testParseToString() {
        String claimString = "#123 @ 3,2: 5x4";
        
        Claim claim = Claim.parse(claimString);
        String result = claim.toString();
        
        Assertions.assertEquals(claimString, result);
    }
    
    /** Test case for {@link Claim#parse(String)} with invalid input. */
    @Test
    public void testParseInvalid() {
        String claimString = "herp derp";
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> Claim.parse(claimString));
        
    }
}
