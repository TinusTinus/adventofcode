package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Current state of all of the pots.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private static final int INITIAL_STATE_PREFIX_LENGTH = "initial state: ".length();
    
    private final Set<Integer> potsWithPlants;
    
    /**
     * Constructor.
     * 
     * @param potsWithPlants list of indices of pots that do contain a plant
     */
    private State(Set<Integer> potsWithPlants) {
        super();
        this.potsWithPlants = potsWithPlants;
    }
    
    /**
     * Parses a string representation of an initial state.
     * 
     * @param line string representation of an initial state, of the form "initial state: #..#.#..##......###...###"
     * @return state
     */
    static State parseInitial(String line) {
        Set<Integer> potsWithPlants = IntStream.range(INITIAL_STATE_PREFIX_LENGTH, line.length())
            .filter(i -> line.charAt(i) == '#')
            .map(i -> i - INITIAL_STATE_PREFIX_LENGTH)
            .boxed()
            .collect(Collectors.toSet());
        
        return new State(potsWithPlants);
    }
    
    // TODO implement toString
}
