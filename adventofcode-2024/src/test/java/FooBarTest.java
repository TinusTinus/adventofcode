import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FooBarTest {
    @Test
    void testFoo() {
        var result = FooBar.toString(FooBar.FOO);
        assertEquals("foo", result);
    }
    
    @Test
    void testBar() {
        var result = FooBar.toString(FooBar.BAR);
        assertEquals("bar", result);
    }
}
