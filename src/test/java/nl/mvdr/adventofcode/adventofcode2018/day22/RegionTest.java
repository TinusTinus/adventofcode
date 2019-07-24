package nl.mvdr.adventofcode.adventofcode2018.day22;

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
        int geologicIndex = 0;
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(0, region.getGeologicIndex());
        Assertions.assertEquals(Type.ROCKY, region.getType());
    }
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample1() {
        int geologicIndex = 16807;
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(16807, region.getGeologicIndex());
        Assertions.assertEquals(Type.WET, region.getType());
    }
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample2() {
        int geologicIndex = 48271;
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(48271, region.getGeologicIndex());
        Assertions.assertEquals(Type.ROCKY, region.getType());
    }
    
    /** Test case for {@link Region#Region(int, int)} based on an example from the puzzle. */
    @Test
    public void testExample3() {
        int geologicIndex = 145722555;
        int depth = 510;
        
        Region region = new Region(geologicIndex, depth);
        
        Assertions.assertEquals(145722555, region.getGeologicIndex());
        Assertions.assertEquals(Type.NARROW, region.getType());
    }
}
