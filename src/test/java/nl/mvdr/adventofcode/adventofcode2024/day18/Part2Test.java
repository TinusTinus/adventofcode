package nl.mvdr.adventofcode.adventofcode2024.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Part2Test {
    
    @Test
    public void test() {
        var solver = new Part2(6);
        
        String result = solver.solve("example-day18-2024.txt");

        Assertions.assertEquals("6,1", result);
    }
}
