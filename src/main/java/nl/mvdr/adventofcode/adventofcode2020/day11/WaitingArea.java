package nl.mvdr.adventofcode.adventofcode2020.day11;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * The waiting area for the ferry to the tropical island.
 *
 * @author Martijn van de Rijdt
 */
record WaitingArea(int width, int height, Set<Point> seats, Set<Point> people) {
    
    /**
     * Parses the textual representation of a waiting area.
     * 
     * @param lines puzzle input
     * @return initial state of the waiting area
     */
    static WaitingArea parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        Set<Point> seats = new HashSet<>();
        for (int y = 0; y != lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                if (line.charAt(x) == 'L') {
                    seats.add(new Point(x, y));
                }
            }
        }
        
        int width = Point.maxX(seats);
        
        return new WaitingArea(width, lines.size(), seats, Set.of());
    }
    
    /**
     * Performs the seating simulation until people stop moving around.
     * 
     * @param getSeatsToConsider function to determine which seats are considered by people when determining whether to sit down
     * @param limit maximum number of occupied considered seats allowed for people to remain seated
     * @return final state of the waiting area after people have stopped moving around
     */
    WaitingArea performSeating(Function<Point, Set<Point>> getSeatsToConsider, long limit) {
        WaitingArea previous = this;
        WaitingArea waitingArea = performRound(getSeatsToConsider, limit);
        while (!waitingArea.equals(previous)) {
            previous = waitingArea;
            waitingArea = waitingArea.performRound(getSeatsToConsider, limit);
        }
        return waitingArea;
    }
    
    /**
     * Applies the seating rules for a single round.
     * 
     * @param getSeatsToConsider function to determine which seats are considered by people when determining whether to sit down
     * @param limit maximum number of occupied considered seats allowed for people to remain seated
     * @return new state of the waiting area
     */
    private WaitingArea performRound(Function<Point, Set<Point>> getSeatsToConsider, long limit) {
        Set<Point> newPeople = seats.parallelStream()
                .filter(seat -> becomesOccupied(seat, getSeatsToConsider, limit))
                .collect(Collectors.toSet());
        return new WaitingArea(width, height, seats, newPeople);
    }

    
    /**
     * Determines for the given seat whether it will be occupied in the next round.
     * 
     * @param seat seat
     * @param getSeatsToConsider function to determine which seats are considered by people when determining whether to sit down
     * @param limit maximum number of occupied considered seats allowed for people to remain seated
     * @return whether the seat will be occupied in the next round
     */
    private boolean becomesOccupied(Point seat, Function<Point, Set<Point>> getSeatsToConsider, long limit) {
        Set<Point> seatsToConsider = getSeatsToConsider.apply(seat);
        
        boolean result;
        if (people.contains(seat)) {
            // The seat is occupied.
            result = seatsToConsider.stream()
                    .filter(people::contains)
                    .count() < limit;
        } else {
            // The seat is empty.
            result = seatsToConsider.stream()
                    .noneMatch(people::contains);
        }
        return result;
    }
    
    /**
     * Finds the closest visible seats in all eight directions, seen from the point of view of the given location.
     * 
     * @param viewpoint the given location
     * @return up to eight closest visible seats
     */
    Set<Point> getClosestVisibleSeats(Point viewpoint) {
        Set<Point> directions = Set.of(
                new Point(1, 0), // east
                new Point(1, 1), // southeast
                new Point(0, 1), // south
                new Point(-1, 1), // southwest
                new Point(-1, 0), // west
                new Point(-1, -1), // northwest
                new Point(0, -1), // north
                new Point(1, -1)); // northeast
        
        return directions.stream()
                .map(direction -> getClosestVisibleSeat(viewpoint, direction))
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .collect(Collectors.toSet());
    }
    
    /**
     * Finds the closest visible seats in the given direction, seen from the point of view of the given location.
     * 
     * @param viewpoint the given location
     * @param direction direction
     * @return up to eight closest visible seats
     */
    private Optional<Point> getClosestVisibleSeat(Point viewpoint, Point direction) {
        return IntStream.range(1, Math.min(width, height))
                .mapToObj(i -> viewpoint.translate(direction.times(i)))
                .filter(seats::contains)
                .min(Comparator.comparing(viewpoint::manhattanDistance));
    }
}
