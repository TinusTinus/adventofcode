package nl.mvdr.adventofcode.adventofcode2024.day06;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record State(Area area, Optional<Guard> guard, Set<Point> visited) {
    
    static State parse(List<String> lines) {
        
        int height = lines.size();
        int width = lines.getFirst().length();
        
        Set<Point> obstructions = new HashSet<>();
        Set<Guard> guards = new HashSet<>();
        
        Point.parse2DMap(lines, (point, c) -> {
            if (c == '#') {
                obstructions.add(point);
            } else if (c != '.') {
                guards.add(new Guard(point, Direction.parse(c)));
            }
        });
        
        var area = new Area(width, height, obstructions);
        
        if (guards.size() != 1) {
            throw new IllegalArgumentException("Expected a single guard");
        }
        var guard = guards.iterator().next();
        
        return new State(area, Optional.of(guard), Set.of(guard.position()));
    }
    
    int predictPath() {
        var state = this;
        while (state.guard().isPresent()) {
            state = state.move();
        }
        return state.visited().size();
    }
    
    private State move() {
        State result;
        
        Point position = guard.orElseThrow().position();
        Direction direction = guard.orElseThrow().direction();
        
        Point nextPosition = direction.move(position);
        if (area.obstructions().contains(nextPosition)) {
            // Turn instead
            Direction nextDirection = direction.turnClockwise();
            var newGuard = new Guard(position, nextDirection);
            result = new State(area, Optional.of(newGuard), visited);
        } else if (area.contains(nextPosition)) {
            // Move
            var newGuard = new Guard(nextPosition, direction);
            Set<Point> newVisited = new HashSet<>(visited);
            newVisited.add(nextPosition);
            result = new State(area, Optional.of(newGuard), newVisited);
        } else {
            // Guard walks out of bounds
            result = new State(area, Optional.empty(), visited);
        }
        return result;
    }
}
