package nl.mvdr.adventofcode.adventofcode2022.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Item}.
 *
 * @author Martijn van de Rijdt
 */
public class ItemTest {
    
    @Test
    public void testPriorityLowercaseA() {
        var item = new Item('a');
        
        var priority = item.priority();
        
        Assertions.assertEquals(1, priority);
    }
    
    @Test
    public void testPriorityLowercaseP() {
        var item = new Item('p');
        
        var priority = item.priority();
        
        Assertions.assertEquals(16, priority);
    }
    
    @Test
    public void testPriorityLowercaseZ() {
        var item = new Item('z');
        
        var priority = item.priority();
        
        Assertions.assertEquals(26, priority);
    }

    @Test
    public void testPriorityUppercaseA() {
        var item = new Item('A');
        
        var priority = item.priority();
        
        Assertions.assertEquals(27, priority);
    }

    @Test
    public void testPriorityUppercaseZ() {
        var item = new Item('Z');
        
        var priority = item.priority();
        
        Assertions.assertEquals(52, priority);
    }
}
