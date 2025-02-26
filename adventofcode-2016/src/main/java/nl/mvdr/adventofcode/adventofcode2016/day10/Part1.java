package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);
    
    private final Microchip firstMicrochip;
    private final Microchip secondMicrochip;
    
    private Part1(Microchip firstMicrochip, Microchip secondMicrochip) {
        this.firstMicrochip = firstMicrochip;
        this.secondMicrochip = secondMicrochip;
    }
    
    Part1(int firstMicrochipValue, int secondMicrochipValue) {
        this(new Microchip(firstMicrochipValue), new Microchip(secondMicrochipValue));
    }
    
    public Part1() {
        this(61, 17);
    }
    
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
                initialMicrochips.computeIfAbsent(holder, h -> new HashSet<>()).add(microchip);
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
        
        var responsibleBot = state.findResponsibleBot(firstMicrochip, secondMicrochip, rules);
        return responsibleBot.number();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day10-2016.txt");

        LOGGER.info(result);
    }
}
