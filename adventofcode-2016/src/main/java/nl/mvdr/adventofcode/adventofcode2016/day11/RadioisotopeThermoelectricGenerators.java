package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

abstract class RadioisotopeThermoelectricGenerators implements IntSolver {

    @Override
    public int solve(Stream<String> lines) {
        var initialState = addExtraItems(State.parse(lines));
        var endState = initialState.endState();
        
        // Search for a path to the end state using Dijkstra's algorithm
        Set<State> visited = new HashSet<>();
        Set<State> current = Set.of(initialState);
        var steps = 0;
        
        while (!current.contains(endState)) {
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
    
    /// Can be overridden to add extra items to the initial state, as required in part two of the puzzle.
    State addExtraItems(State initialState) {
        return initialState;
    }
}
