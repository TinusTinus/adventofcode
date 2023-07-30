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
class ProboscideaVolcanium implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProboscideaVolcanium.class);

    private final boolean helperElephant;
    
    /**
     * Constructor.
     * 
     * @param helperElephant whether one of the elephants can help out
     */
    ProboscideaVolcanium(boolean helperElephant) {
        this.helperElephant = helperElephant;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var network = Network.parse(lines.toList());
        var reachableStates = Set.of(new State(network, helperElephant));
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
}
 