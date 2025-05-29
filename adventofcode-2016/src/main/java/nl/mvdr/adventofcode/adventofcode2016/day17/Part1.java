package nl.mvdr.adventofcode.adventofcode2016.day17;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part1 implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private static final Point VAULT = new Point(3, 3);
    
    @Override
    public String solve(Stream<String> lines) {
        var passcode = lines.findFirst().orElseThrow();
        
        var states = Set.of(new State(passcode));
        
        while (!states.stream().anyMatch(state -> VAULT.equals(state.location())) && !states.isEmpty()) {
            states = states.stream()
                    .flatMap(State::move)
                    .collect(Collectors.toSet());
        }
        
        return states.stream()
                .filter(state -> VAULT.equals(state.location()))
                .reduce((_, _) -> { throw new IllegalStateException("Multiple shortest paths to vault found!"); })
                .orElseThrow(() -> new IllegalStateException("No path to vault found!"))
                .path();
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day17.txt");

        LOGGER.info(result);
    }
}
