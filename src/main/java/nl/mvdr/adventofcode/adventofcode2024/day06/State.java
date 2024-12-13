package nl.mvdr.adventofcode.adventofcode2024.day06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record State(Area area, Optional<GuardPosition> guard, List<GuardPosition> visited) {
    
    static State parse(List<String> lines) {
        
        int height = lines.size();
        int width = lines.getFirst().length();
        
        Set<Point> obstructions = new HashSet<>();
        Set<GuardPosition> guards = new HashSet<>();
        
        Point.parse2DMap(lines, (point, c) -> {
            if (c == '#') {
                obstructions.add(point);
            } else if (c != '.') {
                guards.add(new GuardPosition(point, Direction.parse(c)));
            }
        });
        
        var area = new Area(width, height, obstructions);
        
        if (guards.size() != 1) {
            throw new IllegalArgumentException("Expected a single guard");
        }
        var guard = guards.iterator().next();
        
        return new State(area, Optional.of(guard), List.of());
    }
    
    /// Predicts the path of the guard, until she either leaves the area or starts to loop.
    State predictPath() {
        var state = this;
        while (state.guard().isPresent() && !state.isGuardInLoop()) {
            state = state.move();
        }
        return state;
    }

    boolean isGuardInLoop() {
        return guard.isPresent() && visited.contains(guard.orElseThrow());
    }
    
    private State move() {
        
        Point position = guard.orElseThrow().position();
        Direction direction = guard.orElseThrow().direction();
        
        Optional<GuardPosition> newGuard;
        
        Point nextPosition = direction.move(position);
        if (area.obstructions().contains(nextPosition)) {
            // Turn instead
            Direction nextDirection = direction.turnClockwise();
            newGuard = Optional.of(new GuardPosition(position, nextDirection));
        } else if (area.contains(nextPosition)) {
            // Move
            newGuard = Optional.of(new GuardPosition(nextPosition, direction));
        } else {
            // Leave the area
            newGuard = Optional.empty();
        }
        
        List<GuardPosition> newVisited = new ArrayList<>(visited);
        newVisited.add(guard.orElseThrow());
        
        return new State(area, newGuard, newVisited);
    }
    
    State addObstruction(Point obstruction) {
        return new State(area.addObstruction(obstruction), guard, visited);
    }
}
