package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Region}.
 * 
 * @author Martijn van de Rijdt
 */
public class RegionTest {
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample0() {
        BigInteger geologicIndex = BigInteger.ZERO;
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(geologicIndex, region.getGeologicIndex());
        Assertions.assertEquals(Type.ROCKY, region.getType());
    }
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample1() {
        BigInteger geologicIndex = BigInteger.valueOf(16_807L);
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(geologicIndex, region.getGeologicIndex());
        Assertions.assertEquals(Type.WET, region.getType());
    }
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample2() {
        BigInteger geologicIndex = BigInteger.valueOf(48_271L);
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(geologicIndex, region.getGeologicIndex());
        Assertions.assertEquals(Type.ROCKY, region.getType());
    }
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample3() {
        BigInteger geologicIndex = BigInteger.valueOf(145_722_555L);
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(geologicIndex, region.getGeologicIndex());
        Assertions.assertEquals(Type.NARROW, region.getType());
    }
}
