package nl.mvdr.adventofcode.adventofcode2024.day20;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

class RaceConditionSolver implements LongSolver {

    private final int maxCheatDuration;
    private final int minSavings;
    
    RaceConditionSolver(int maxCheatDuration, int minSavings) {
        this.maxCheatDuration = maxCheatDuration;
        this.minSavings = minSavings;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var maze = Maze.parse(lines.toList());
        return maze.countCheats(maxCheatDuration, minSavings);
    }
}
 