package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/16">Proboscidea Volcanium</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ProboscideaVolcaniumPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProboscideaVolcaniumPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        // TODO
        var network = Network.parse(lines.toList());
        var reachableStates = Set.of(new State(network, true));
        while (0 < reachableStates.iterator().next().remainingMinutes()) {
            reachableStates = reachableStates.stream()
                    .parallel()
                    .flatMap(state -> state.nextStates().stream())
                    .collect(Collectors.toSet());
            LOGGER.debug("Possible states: {}", Integer.valueOf(reachableStates.size()));
        }
        return reachableStates.stream()
                .mapToInt(State::pressureReleased)
                .max()
                .orElseThrow();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ProboscideaVolcaniumPart2();

        var result = instance.solve("input-day16-2022.txt");

        LOGGER.info(result);
    }
}
 