package nl.mvdr.adventofcode.adventofcode2016.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Part2Test {
    
    @Test
    void testSolution() {
        var solver = new Part2(9);
        
        var result = solver.solve("example-day20.txt");
        
        assertEquals("2", result);
    }
}
