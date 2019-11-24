package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to the day 20 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/20">A Regular Map</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RegularMap implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RegularMap.class);
    
    @Override
    public String solve(Stream<String> lines) {
        String expressionString = lines.findFirst().orElseThrow();
        
        RoomMapExpression expression = RoomMapExpression.parse(expressionString);
        
        LOGGER.debug("Expression: {}", expression);
        
        RoomMap map = RoomMap.createMap(expression);
        
        return "Part 1: " + map.calculateShortestDistanceToFurthestRoom() + ", part 2: " + map.calculateNumberOfRoomsOver1000DoorsAway();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RegularMap solver = new RegularMap();
        String solution = solver.solve("input-day20-2018.txt");
        LOGGER.info(solution);
    }
}
