package nl.mvdr.adventofcode.adventofcode2019.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SecureContainerPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SecureContainerPart1Test extends SolverTest<SecureContainerPart1> {

    /** Constructor. */
    public SecureContainerPart1Test() {
        super(SecureContainerPart1.class);
    }
    
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
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("1640", "input-day04-2019.txt"); 
    }
}
