package nl.mvdr.adventofcode.adventofcode2016.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.point.Point;

class Part1Test {
    
    @Test
    void testExample() {
        var solver = new Part1(new Point(7, 4));
        
        var result = solver.solve("example-day13.txt");
        
        assertEquals("11", result);
    }
}
