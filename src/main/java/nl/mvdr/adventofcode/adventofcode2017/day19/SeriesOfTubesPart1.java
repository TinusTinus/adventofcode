package nl.mvdr.adventofcode.adventofcode2017.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 19 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/19">A Series of Tubes</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart1 implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesOfTubesPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the letters the packet sees on its path
     */
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> map = Files.lines(inputFilePath)
                .collect(Collectors.toList());
        
        String topLine = map.get(0);
        int startX = IntStream.range(0, topLine.length())
                .filter(i -> topLine.charAt(i) == '|')
                .findFirst()
                .getAsInt();
        
        Packet packet = new Packet(new Point(startX, 0), Direction.DOWN);
        String result = "";
        
        boolean stopped = false;
        while (!stopped) {
            Point location = packet.getLocation();
            char currentChar = getCharacterAt(map, location);
            
            if (Character.isLetter(currentChar)) {
                result = result + currentChar;
            }
            
            List<Direction> potentialNextDirections;
            if (currentChar == '+') {
                // Turn
                potentialNextDirections = List.of(packet.getDirection().turnClockwise(), packet.getDirection().turnCounterClockwise());
            } else {
                potentialNextDirections = List.of(packet.getDirection());
            }
            Optional<Packet> next = potentialNextDirections.stream()
                    .map(direction -> new Packet(direction.move(location), direction))
                    .filter(p -> 0 <= p.getLocation().getY())
                    .filter(p -> p.getLocation().getY() < map.size())
                    .filter(p -> 0 <= p.getLocation().getX())
                    .filter(p -> p.getLocation().getX() < map.get(p.getLocation().getY()).length())
                    .filter(p -> getCharacterAt(map, p.getLocation()) != ' ')
                    .findFirst();
            if (next.isPresent()) {
                packet = next.get();
            } else {
                stopped = true;
            }
        }
        
        return result;
    }
    
    private char getCharacterAt(List<String> map, Point location) {
        int x = location.getX();
        int y = location.getY();
        
        return map.get(y).charAt(x);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SeriesOfTubesPart1 instance = new SeriesOfTubesPart1();

        String result = instance.solve("input-day19-2017.txt");

        LOGGER.info(result);
    }
}
