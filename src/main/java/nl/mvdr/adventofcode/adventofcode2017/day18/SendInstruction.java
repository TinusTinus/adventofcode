package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction snd X sends the value of X to a target program.
 *
 * @author Martijn van de Rijdt
 */
class SendInstruction implements Instruction {

    /** Keyword for this instruction. */
    static final String NAME = "snd";
    
    /** Value to send. */
    private final String value;
    
    /**
     * Constructor.
     * 
     * @param value value to send
     */
    SendInstruction(String value) {
        super();
        this.value = value;
    }
    
    @Override
    public State execute(State startState) {
        State result = startState.send(getValue(startState));
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }
    
    /**
     * Gets the value to send.
     * 
     * @param state current state
     * @return value
     */
    long getValue(State state) {
        long result;
        try {
            result = Integer.parseInt(value);
        } catch (@SuppressWarnings("unused") NumberFormatException e) {
            result = state.getRegisterValue(value);
        }
        return result;
    }
    
    @Override
    public String toString() {
        return NAME + " " + value;
    }
}
