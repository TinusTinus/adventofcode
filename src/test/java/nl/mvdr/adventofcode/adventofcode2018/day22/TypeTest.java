package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.math.BigInteger;

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
        BigInteger erosionLevel = BigInteger.valueOf(510);
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.ROCKY, type);
    }
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample1() {
        BigInteger erosionLevel = BigInteger.valueOf(17317);
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.WET, type);
    }
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample2() {
        BigInteger erosionLevel = BigInteger.ZERO;
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.ROCKY, type);
    }
    
    /** Test case for {@link Type#getType(int)} based on an example from the puzzle. */
    @Test
    public void testGetTypeExample3() {
        BigInteger erosionLevel = BigInteger.valueOf(1805);
        
        Type type = Type.getType(erosionLevel);
        
        Assertions.assertEquals(Type.NARROW, type);
    }
}
