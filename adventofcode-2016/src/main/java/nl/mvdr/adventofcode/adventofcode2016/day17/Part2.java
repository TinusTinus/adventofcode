package nl.mvdr.adventofcode.adventofcode2016.day17;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part2 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    private static final Point VAULT = new Point(3, 3);
    
    @Override
    public int solve(Stream<String> lines) {
        var passcode = lines.findFirst().orElseThrow();
        
        var states = Set.of(new State(passcode));
        
        int longestPathLength = 0;
        
        while (!states.isEmpty()) {
            states = states.stream()
                    .flatMap(State::move)
                    .collect(Collectors.toSet());
            
            longestPathLength = states.stream()
                    .filter(state -> VAULT.equals(state.location()))
                    .mapToInt(state -> state.path().length())
                    .findFirst()
                    .orElse(longestPathLength);
        }
        
        return longestPathLength;
    }

    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day17.txt");

        LOGGER.info(result);
    }
}
