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
     * @param roundFunction the function to simulate a single round; see {@link #performRoundPart1()} or {@link #performRoundPart2()}
     * @return final state of the waiting area after people have stopped moving around
     */
    WaitingArea performSeating(Function<WaitingArea, WaitingArea> roundFunction) {
        WaitingArea previous = this;
        WaitingArea waitingArea = roundFunction.apply(this);
        while (!waitingArea.equals(previous)) {
            previous = waitingArea;
            waitingArea = roundFunction.apply(waitingArea);
        }
        return waitingArea;
    }
    
    /**
     * Applies the seating rules from part 1 of the puzzle for a single round.
     * 
     * @return new state of the waiting area
     */
    WaitingArea performRoundPart1() {
        Set<Point> newPeople = new HashSet<>();
        for (Point seat : seats) {
            if (people.contains(seat)) {
                // The seat is occupied.
                if (seat.neighboursIncludingDiagonals().stream()
                        .filter(people::contains)
                        .count() < 4L) {
                    // The seat stays occupied.
                    newPeople.add(seat);
                } // Otherwise: four or more adjacent seats are occupied. The seat becomes empty.
            } else {
                // The seat is empty.
                if (seat.neighboursIncludingDiagonals().stream()
                        .noneMatch(people::contains)) {
                    // There are no occupied seats adjacent to it.
                    // The seat becomes occupied.
                    newPeople.add(seat);
                } // Otherwise: the seat stays empty.
            }
        }
        
        return new WaitingArea(width, height, seats, newPeople);
    }
    
    /**
     * Applies the seating rules from part 2 of the puzzle for a single round.
     * 
     * @return new state of the waiting area
     */
    WaitingArea performRoundPart2() {
        Set<Point> newPeople = seats.parallelStream()
                .filter(this::becomesOccupiedPart2)
                .collect(Collectors.toSet());
        return new WaitingArea(width, height, seats, newPeople);
    }
    
    /**
     * Determines for the given seat whether it will be occupied in the next round,
     * according to the seating rules from part 2.
     * 
     * @param seat seat
     * @return whether the seat will be occupied
     */
    private boolean becomesOccupiedPart2(Point seat) {
        Set<Point> seatsToConsider = getClosestVisibleSeats(seat);
        
        boolean result;
        if (people.contains(seat)) {
            // The seat is occupied.
            result = seatsToConsider.stream()
                    .filter(people::contains)
                    .count() < 5L;
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
    private Set<Point> getClosestVisibleSeats(Point viewpoint) {
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
