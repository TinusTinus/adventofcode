package nl.mvdr.adventofcode.adventofcode2022.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Pair}.
 *
 * @author Martijn van de Rijdt
 */
public class PairTest {
    @Test
    public void testOverlaps0() {
        var pair = new Pair(new Assignment(2, 4), new Assignment(6, 8));
        
        var result = pair.overlaps();
        
        Assertions.assertFalse(result);
    }
    
    @Test
    public void testOverlaps1() {
        var pair = new Pair(new Assignment(2, 3), new Assignment(4, 5));
        
        var result = pair.overlaps();
        
        Assertions.assertFalse(result);
    }
    
    @Test
    public void testOverlaps2() {
        var pair = new Pair(new Assignment(5, 7), new Assignment(7, 9));
        
        var result = pair.overlaps();
        
        Assertions.assertTrue(result);
    }
    
    @Test
    public void testOverlaps3() {
        var pair = new Pair(new Assignment(2, 8), new Assignment(3, 7));
        
        var result = pair.overlaps();
        
        Assertions.assertTrue(result);
    }
    
    @Test
    public void testOverlaps4() {
        var pair = new Pair(new Assignment(6, 6), new Assignment(4, 6));
        
        var result = pair.overlaps();
        
        Assertions.assertTrue(result);
    }
    
    @Test
    public void testOverlaps5() {
        var pair = new Pair(new Assignment(2, 6), new Assignment(4, 8));
        
        var result = pair.overlaps();
        
        Assertions.assertTrue(result);
    }
}
