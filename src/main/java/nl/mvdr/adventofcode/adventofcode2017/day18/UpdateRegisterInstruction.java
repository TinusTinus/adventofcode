package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * Instruction which updates a register value.
 *
 * @author Martijn van de Rijdt
 */
abstract class UpdateRegisterInstruction implements Instruction {
    
    private final String register;
    private final String value;
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    UpdateRegisterInstruction(String register, String value) {
        super();
        this.register = register;
        this.value = value;
    }
    
    @Override
    public State execute(State startState) {
        int oldValue = startState.getRegisterValue(register);
        
        State result = startState.updateRegister(register, computeNewValue(oldValue, startState));
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }

    /**
     * Gets the integer value for the second parameter for this instruction.
     * 
     * @param state current state
     * @return value
     */
    int getValue(State state) {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (@SuppressWarnings("unused") NumberFormatException e) {
            result = state.getRegisterValue(value);
        }
        return result;
    }
    
    /**
     * Computes the new value for the register.
     * 
     * @param oldValue old value of the register
     * @param state start state
     * @return new value
     */
    abstract int computeNewValue(int oldValue, State state);
    
    /** @return keyword for the specific instruction */
    abstract String getName();
    
    @Override
    public String toString() {
        return getName() + " " + register + " " + value;
    }
}
