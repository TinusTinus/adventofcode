package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.Map;
import java.util.Set;

/**
 * Static definition of a Turing machine.
 *
 * @author Martijn van de Rijdt
 */
class TuringMachineDefinition {
    /** Name of the initial state. */
    private final String initialState;
    
    /** States of the machine, indexed by name. */
    private final Map<String, State> states;

    /**
     * Constructor.
     * 
     * @param initialState name of the initial state
     * @param states states of the machine, indexed by name
     */
    TuringMachineDefinition(String initialState, Map<String, State> states) {
        super();
        this.initialState = initialState;
        this.states = states;
    }
    
    /** @return new Turing machine in its initial state */
    TuringMachine createTuringMachine() {
        return new TuringMachine(this, Set.of(), 0, initialState);
    }
    
    /**
     * Retrieves a state.
     * 
     * @param name name of the state
     * @return state with the given name
     */
    State getState(String name) {
        return states.get(name);
    }
}
