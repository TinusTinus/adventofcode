package nl.mvdr.adventofcode.adventofcode2016.day19;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

abstract class ElephantNamedJosephSolver implements IntSolver {

    @Override
    public int solve(Stream<String> lines) {
        var line = lines.findFirst().orElseThrow();
        var startingElves = Integer.parseInt(line);
        return solve(startingElves);
    }
    
    protected abstract int solve(int startingElves);
}
