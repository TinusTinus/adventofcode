package nl.mvdr.adventofcode.adventofcode2017.duet;

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
        long oldValue = startState.getRegisterValue(register);
        
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
    long getValue(State state) {
        return Instruction.getValue(value, state);
    }
    
    /**
     * Computes the new value for the register.
     * 
     * @param oldValue old value of the register
     * @param state start state
     * @return new value
     */
    abstract long computeNewValue(long oldValue, State state);
    
    /** @return keyword for the specific instruction */
    abstract String getName();
    
    @Override
    public String toString() {
        return getName() + " " + register + " " + value;
    }
}
