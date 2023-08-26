package nl.mvdr.adventofcode.adventofcode2022.day21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Operator}.
 *
 * @author Martijn van de Rijdt
 */
public class OperatorTest {

    /**
     * Test case for {@link Operator#apply(NumberValue, NumberValue)}.
     */
    @Test
    public void testApplyPlus() {
        NumberValue lhs = new NumberValue(2L);
        NumberValue rhs = new NumberValue(3L);
        
        NumberValue result = Operator.PLUS.apply(lhs, rhs);
        
        Assertions.assertEquals(new NumberValue(5L), result);
    }
    
    /**
     * Test case for {@link Operator#apply(NumberValue, NumberValue)}.
     */
    @Test
    public void testApplyMinus() {
        NumberValue lhs = new NumberValue(2L);
        NumberValue rhs = new NumberValue(3L);
        
        NumberValue result = Operator.MINUS.apply(lhs, rhs);
        
        Assertions.assertEquals(new NumberValue(-1L), result);
    }

    /**
     * Test case for {@link Operator#apply(NumberValue, NumberValue)}.
     */
    @Test
    public void testApplyTimes() {
        NumberValue lhs = new NumberValue(2L);
        NumberValue rhs = new NumberValue(3L);
        
        NumberValue result = Operator.TIMES.apply(lhs, rhs);
        
        Assertions.assertEquals(new NumberValue(6L), result);
    }
    
    /**
     * Test case for {@link Operator#apply(NumberValue, NumberValue)}.
     */
    @Test
    public void testApplyDivide() {
        NumberValue lhs = new NumberValue(15L);
        NumberValue rhs = new NumberValue(5L);
        
        NumberValue result = Operator.DIVIDE.apply(lhs, rhs);
        
        Assertions.assertEquals(new NumberValue(3L), result);
    }
    
    /**
     * Test case for {@link Operator#findLhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindLhsPlus() {
        var rhs = new NumberValue(3L);
        var target = 5L;
        
        var result = Operator.PLUS.findLhs(rhs, target);
        
        Assertions.assertEquals(2L, result);
    }
    
    /**
     * Test case for {@link Operator#findRhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindRhsPlus() {
        var lhs = new NumberValue(3L);
        var target = 5L;
        
        var result = Operator.PLUS.findRhs(lhs, target);
        
        Assertions.assertEquals(2L, result);
    }
    
    /**
     * Test case for {@link Operator#findLhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindLhsMinus() {
        var rhs = new NumberValue(3L);
        var target = 5L;
        
        var result = Operator.MINUS.findLhs(rhs, target);
        
        Assertions.assertEquals(8L, result);
    }
    
    /**
     * Test case for {@link Operator#findRhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindRhsMinus() {
        var lhs = new NumberValue(3L);
        var target = 5L;
        
        var result = Operator.MINUS.findRhs(lhs, target);
        
        Assertions.assertEquals(-2L, result);
    }
    
    /**
     * Test case for {@link Operator#findLhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindLhsTimes() {
        var rhs = new NumberValue(3L);
        var target = 15L;
        
        var result = Operator.TIMES.findLhs(rhs, target);
        
        Assertions.assertEquals(5L, result);
    }
    
    /**
     * Test case for {@link Operator#findRhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindRhsTimes() {
        var lhs = new NumberValue(3L);
        var target = 15L;
        
        var result = Operator.TIMES.findRhs(lhs, target);
        
        Assertions.assertEquals(5L, result);
    }
    
    /**
     * Test case for {@link Operator#findLhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindLhsDivide() {
        var rhs = new NumberValue(5L);
        var target = 3L;
        
        var result = Operator.DIVIDE.findLhs(rhs, target);
        
        Assertions.assertEquals(15L, result);
    }
    
    /**
     * Test case for {@link Operator#findRhs(NumberValue, NumberValue)}.
     */
    @Test
    public void testFindRhsDivide() {
        var lhs = new NumberValue(15L);
        var target = 5L;
        
        var result = Operator.DIVIDE.findRhs(lhs, target);
        
        Assertions.assertEquals(3L, result);
    }
}
