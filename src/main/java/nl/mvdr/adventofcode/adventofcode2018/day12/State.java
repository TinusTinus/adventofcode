package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.util.HashSet;
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
    
    /**
     * Computes the next generation of plants.
     * 
     * Note that this method assumes that the rule ..... => . is present.
     * This is the case in the provided example and in the puzzle input.
     * 
     * @param notes notes containing the rules for plant generation
     * @return next generation
     */
    State nextGeneration(Set<Note> notes) {
        State result;
        if (potsWithPlants.isEmpty()) {
            result = this;
        } else {
            int startIndex = potsWithPlants.stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .getAsInt() - 2;
            int endIndex = potsWithPlants.stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .getAsInt() + 2;
            
            Set<Integer> nextPotsWithPlants = new HashSet<>();
            for (int currentPot = startIndex; currentPot <= endIndex; currentPot++) {
                if (willHavePlant(currentPot, notes)) {
                    nextPotsWithPlants.add(Integer.valueOf(currentPot));
                }
            }
            result = new State(nextPotsWithPlants);
        }
              
        return result;
    }
    
    /** @return value of this state */
    int getValue() {
        return potsWithPlants.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
    
    /**
     * Determines whether the pot with the given index will contain a plant in the next generation.
     * 
     * @param pot pot index
     * @param notes notes containing the rules for plant generation
     * @return whether the pot will contain a plant
     */
    private boolean willHavePlant(int pot, Set<Note> notes) {
        return notes.stream()
                .filter(note -> note.hasPrecondition(hasPlant(pot - 2), hasPlant(pot - 1), hasPlant(pot), hasPlant(pot + 1), hasPlant(pot + 2)))
                // there should be exactly one note that satisfies this precondition
                .findFirst()
                .get()
                .isNext();
    }
    
    /**
     * Checks whether the pot with the given index currently contains a plant. 
     * 
     * @param pot pot index
     * @return whether the pot contains a plant
     */
    private boolean hasPlant(int pot) {
        return potsWithPlants.contains(Integer.valueOf(pot));
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
