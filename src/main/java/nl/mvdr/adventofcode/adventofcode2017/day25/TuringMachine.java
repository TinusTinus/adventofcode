package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.HashSet;
import java.util.Set;

/**
 * A Turing machine.
 * 
 * Note: this class is mutable.
 *
 * @author Martijn van de Rijdt
 */
class TuringMachine {
    /** Definition of the Turing machine. */
    private final TuringMachineDefinition definition;
    
    /** Representation of the tape. The set contains slots where the value is equal to 1. All other slot values are 0. */
    private final Set<Integer> tape;
    
    /** Cursor in the tape, which can move left (negative values) or right (positive values). */
    private int cursor;
    
    /** Name of the next state to be exectuted. */
    private String nextState;

    /**
     * Constructor.
     * 
     * @param definition definition of the Turing machine
     * @param tape representation of the tape
     * @param cursor cursor
     * @param nextState name of the next state
     */
    TuringMachine(TuringMachineDefinition definition, String initialState) {
        super();
        this.definition = definition;
        this.tape = new HashSet<>();
        this.cursor = 0;
        this.nextState = initialState;
    }
    
    /** Executes a single step. */
    void executeStep() {
        State state = definition.getState(nextState);
        state.execute(this);
    }
    
    /** @return diagnostic checksum */
    int checksum() {
        return tape.size();
    }
    
    TuringMachineDefinition getDefinition() {
        return definition;
    }
    
    Set<Integer> getTape() {
        return tape;
    }
    
    int getCursor() {
        return cursor;
    }
    
    String getNextState() {
        return nextState;
    }
    
    void setCursor(int cursor) {
        this.cursor = cursor;
    }
    
    void setNextState(String nextState) {
        this.nextState = nextState;
    }
}
