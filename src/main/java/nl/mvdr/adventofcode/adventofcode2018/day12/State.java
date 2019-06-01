package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.util.OptionalInt;
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
                .filter(i -> line.charAt(i) == PlantConstants.PLANT)
                .map(i -> i - INITIAL_STATE_PREFIX_LENGTH)
                .boxed()
                .collect(Collectors.toSet());
        
        return new State(potsWithPlants);
    }
    
    @Override
    public String toString() {
        OptionalInt optionalFirstPlant = potsWithPlants.stream()
                .mapToInt(Integer::intValue)
                .min();
        
        StringBuilder result = new StringBuilder();
        if (optionalFirstPlant.isPresent()) {
            int firstPlant = optionalFirstPlant.getAsInt();
            
            result.append(firstPlant);
            result.append(": ");
            
            int lastPlant = potsWithPlants.stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .getAsInt();
            for (int i = firstPlant; i <= lastPlant; i++) {
                if (potsWithPlants.contains(Integer.valueOf(i))) {
                    result.append(PlantConstants.PLANT);
                } else {
                    result.append(PlantConstants.NO_PLANT);
                }
            }
        }
        
        return result.toString();
    }
}
