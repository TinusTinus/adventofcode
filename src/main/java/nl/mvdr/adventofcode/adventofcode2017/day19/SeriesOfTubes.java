package nl.mvdr.adventofcode.adventofcode2017.day19;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LinesSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 19 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/19">A Series of Tubes</a>.
 * 
 * @param <R> result type
 *
 * @author Martijn van de Rijdt
 */
abstract class SeriesOfTubes<R> implements LinesSolver<R> {

    @Override
    public R solve(Stream<String> lines) {
        List<String> map = lines.collect(Collectors.toList());
        
        String topLine = map.get(0);
        int startX = IntStream.range(0, topLine.length())
                .filter(i -> topLine.charAt(i) == '|')
                .findFirst()
                .getAsInt();
        
        Packet packet = new Packet(new Point(startX, 0), Direction.DOWN);
        String letters = "";
        int steps = 0;
        
        boolean stopped = false;
        while (!stopped) {
            Point location = packet.getLocation();
            char currentChar = getCharacterAt(map, location);
            
            if (Character.isLetter(currentChar)) {
                letters = letters + currentChar;
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
            steps++;
        }
        
        return solve(letters, steps);
    }

    /**
     * Solver method.
     * 
     * @param the letters the packet sees on its path
     * @param the number of steps taken by the packet
     * @return solution to the puzzle for the given input
     */
    abstract R solve(String letters, int steps);
    
    private char getCharacterAt(List<String> map, Point location) {
        int x = location.getX();
        int y = location.getY();
        
        return map.get(y).charAt(x);
    }
}
