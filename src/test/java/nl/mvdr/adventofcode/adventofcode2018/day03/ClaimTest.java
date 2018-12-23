package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.Set;

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
    
    /** Test case for {@link Claim#getFabric()}. */
    @Test
    public void testGetFabric() {
        String claimString = "#123 @ 3,2: 5x4";
        Claim claim = Claim.parse(claimString);
        
        Set<SquareInch> fabric = claim.getFabric();
        
        Assertions.assertEquals(20, fabric.size());
        Assertions.assertTrue(fabric.contains(new SquareInch(3, 2)));
        Assertions.assertTrue(fabric.contains(new SquareInch(4, 2)));
        Assertions.assertTrue(fabric.contains(new SquareInch(5, 2)));
        Assertions.assertTrue(fabric.contains(new SquareInch(6, 2)));
        Assertions.assertTrue(fabric.contains(new SquareInch(7, 2)));
        Assertions.assertTrue(fabric.contains(new SquareInch(3, 3)));
        Assertions.assertTrue(fabric.contains(new SquareInch(4, 3)));
        Assertions.assertTrue(fabric.contains(new SquareInch(5, 3)));
        Assertions.assertTrue(fabric.contains(new SquareInch(6, 3)));
        Assertions.assertTrue(fabric.contains(new SquareInch(7, 3)));
        Assertions.assertTrue(fabric.contains(new SquareInch(3, 4)));
        Assertions.assertTrue(fabric.contains(new SquareInch(4, 4)));
        Assertions.assertTrue(fabric.contains(new SquareInch(5, 4)));
        Assertions.assertTrue(fabric.contains(new SquareInch(6, 4)));
        Assertions.assertTrue(fabric.contains(new SquareInch(7, 4)));
        Assertions.assertTrue(fabric.contains(new SquareInch(3, 5)));
        Assertions.assertTrue(fabric.contains(new SquareInch(4, 5)));
        Assertions.assertTrue(fabric.contains(new SquareInch(5, 5)));
        Assertions.assertTrue(fabric.contains(new SquareInch(6, 5)));
        Assertions.assertTrue(fabric.contains(new SquareInch(7, 5)));
    }
}
