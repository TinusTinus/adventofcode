package nl.mvdr.adventofcode.adventofcode2019.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link SecureContainerPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart1Test {

    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate111111() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 111111;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertTrue(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate122345() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 122345;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertTrue(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate111123() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 111123;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertTrue(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate135679() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 135679;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertFalse(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate223450() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 223450;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertFalse(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate123789() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 123789;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertFalse(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate112233() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 112233;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertTrue(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate123444() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 123444;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertTrue(valid);
    }
    
    /** Test case for {@link SecureContainerPart1#validatePassword(int)}.  */
    @Test
    public void testValidate111122() {
        SecureContainerPart1 solver = new SecureContainerPart1();
        int password = 111122;
        
        boolean valid = solver.validatePassword(password);
        
        Assertions.assertTrue(valid);
    }
}
