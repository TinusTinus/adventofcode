package nl.mvdr.adventofcode.adventofcode2016.day21;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Part2Test {
    
    @Test
    void testExample() {
        var solver = new Part2("decab");
        
        var result = solver.solve("example-day21.txt");
        
        assertTrue(result.contains("abcde"));
    }
}
