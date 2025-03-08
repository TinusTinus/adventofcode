package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

class RadioisotopeThermoelectricGenerators implements IntSolver {

    private final boolean extraObjects;
    
    RadioisotopeThermoelectricGenerators(boolean extraObjects) {
        this.extraObjects = extraObjects;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var initialState = State.parse(lines, extraObjects);
        var endState = initialState.endState();
        
        // Search for a path to the end state using Dijkstra's algorithm
        Set<State> visited = new HashSet<>();
        Set<State> current = Set.of(initialState);
        var steps = 0;
        
        while (!current.contains(endState)) {
            if (current.isEmpty()) {
                throw new IllegalStateException("No path found");
            }
            
            Set<State> next = current.parallelStream()
                    .flatMap(State::takeElevator)
                    .distinct()
                    .filter(Predicate.not(visited::contains))
                    .collect(Collectors.toSet());
            visited.addAll(current);
            current = next;
            steps++;
        }
        
        return steps;
    }
}
