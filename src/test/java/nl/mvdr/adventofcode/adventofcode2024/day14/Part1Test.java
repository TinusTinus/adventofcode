package nl.mvdr.adventofcode.adventofcode2024.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Part1Test {

    @Test
    void testExample() {
        Part1 solver = new Part1(11, 7);
        
        var result = solver.solve("example-day14-2024.txt");
        
        Assertions.assertEquals("12", result);
    }
}
