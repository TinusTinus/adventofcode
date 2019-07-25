package nl.mvdr.adventofcode.adventofcode2018.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Type}.
 * 
 * @author Martijn van de Rijdt
 */
public class TypeTest {
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample0() {
        int erosionLevel = 510;
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.ROCKY, type);
    }
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample1() {
        int erosionLevel = 17317;
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.WET, type);
    }
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample2() {
        int erosionLevel = 0;
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.ROCKY, type);
    }
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample3() {
        int erosionLevel = 1805;
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.NARROW, type);
    }
}
