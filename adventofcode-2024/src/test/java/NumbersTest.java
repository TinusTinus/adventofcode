import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumbersTest {
    @Test
    void testInteger() {
        var result = Numbers.toString(3);
        
        assertEquals("int", result);
    }
    
    @Test
    void testLong() {
        var result = Numbers.toString(32132132133213L);
        
        assertEquals("long", result);
    }
    
    @Test
    void testFloat() {
        var result = Numbers.toString(3.f);
        
        assertEquals("other", result);
    }
}
