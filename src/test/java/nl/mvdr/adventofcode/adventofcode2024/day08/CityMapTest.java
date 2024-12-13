package nl.mvdr.adventofcode.adventofcode2024.day08;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.point.Point;

public class CityMapTest {
    @Test
    public void test() {
        var map = new CityMap(10, 10, Map.of());
        
        var antinodes = map.antinodes(new Point(4, 3), new Point(5, 5), true);
        
        Assertions.assertEquals(2, antinodes.size(), "Antinodes found: " + antinodes);
        Assertions.assertTrue(antinodes.contains(new Point(3 , 1)), "Antinodes found: " + antinodes);
        Assertions.assertTrue(antinodes.contains(new Point(6 , 7)), "Antinodes found: " + antinodes);
    }
}
