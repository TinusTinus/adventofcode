package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Part1Test {
    
    @Test
    void testExample() {
        var solver = new Part1("abcde");
        
        var result = solver.solve("example-day21.txt");
        
        assertEquals("decab", result);
    }
}
