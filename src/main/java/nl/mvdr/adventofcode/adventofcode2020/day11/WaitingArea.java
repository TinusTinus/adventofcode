package nl.mvdr.adventofcode.adventofcode2020.day11;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * The waiting area for the ferry to the tropical island.
 *
 * @author Martijn van de Rijdt
 */
record WaitingArea(Set<Point> seats, Set<Point> people) {
    
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
        
        return new WaitingArea(seats, Set.of());
    }
    
    /**
     * @return final state of the waiting area after people have stopped moving around
     */
    WaitingArea performSeating() {
        WaitingArea previous = this;
        WaitingArea waitingArea = performRound();
        while (!waitingArea.equals(previous)) {
            previous = waitingArea;
            waitingArea = waitingArea.performRound();
        }
        return waitingArea;
    }
    
    /**
     * Applies the searing rules for a single round.
     * 
     * @return new state of the waiting area
     */
    private WaitingArea performRound() {
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
        
        return new WaitingArea(seats, newPeople);
    }
}
