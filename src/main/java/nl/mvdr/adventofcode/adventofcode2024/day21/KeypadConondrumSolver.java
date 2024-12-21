package nl.mvdr.adventofcode.adventofcode2024.day21;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

class KeypadConondrumSolver implements IntSolver {

    private final int intermediateRobots;
    
    KeypadConondrumSolver(int intermediateRobots) {
        this.intermediateRobots = intermediateRobots;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        return lines.map(code -> new State(code, intermediateRobots))
                .parallel()
                .mapToInt(State::complexity)
                .sum();
    }
}
 