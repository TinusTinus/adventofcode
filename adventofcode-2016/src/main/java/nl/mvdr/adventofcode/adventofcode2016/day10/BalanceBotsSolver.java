package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

abstract class BalanceBotsSolver implements IntSolver {
    
    @Override
    public int solve(Stream<String> lines) {
        Map<Bot, Rule> rules = new HashMap<>();
        Map<MicrochipHolder, Set<Microchip>> initialMicrochips = new HashMap<>();
        lines.forEach(line -> {
            if (line.startsWith("value ")) {
                var parts = line.substring(6).split(" goes to ");
                var value = Integer.parseInt(parts[0]);
                var microchip = new Microchip(value);
                var holder = MicrochipHolder.parse(parts[1]);
                initialMicrochips.computeIfAbsent(holder, _ -> new HashSet<>()).add(microchip);
            } else if (line.startsWith("bot ")) {
                var parts = line.split(" gives low to ");
                var bot = Bot.parse(parts[0]);
                parts = parts[1].split(" and high to ");
                var lowTarget = MicrochipHolder.parse(parts[0]);
                var highTarget = MicrochipHolder.parse(parts[1]);
                var rule = new Rule(lowTarget, highTarget);
                rules.put(bot, rule);
            } else {
                throw new IllegalArgumentException("Unable to parse line: " + line);
            }
        });
        
        var state = new State(initialMicrochips);
        
        return solve(state, rules);
    }

    protected abstract int solve(State initialState, Map<Bot, Rule> rules);
}
