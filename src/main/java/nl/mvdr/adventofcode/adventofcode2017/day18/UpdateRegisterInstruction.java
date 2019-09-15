package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * Instruction which updates a register value.
 *
 * @author Martijn van de Rijdt
 */
abstract class UpdateRegisterInstruction implements Instruction {

    private final String register;
    private final int value;
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    UpdateRegisterInstruction(String register, int value) {
        super();
        this.register = register;
        this.value = value;
    }
    
    @Override
    public State execute(State startState) {
        int oldValue = startState.getRegisterValue(register);
        
        State result = startState.updateRegister(register, computeNewValue(oldValue));
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }

    /**
     * Computes the new value for the register.
     * 
     * @param oldValue old value of the register
     * @return new value
     */
    abstract int computeNewValue(int oldValue);

    int getValue() {
        return value;
    }
    
    /** @return keyword for the specific instruction */
    abstract String getName();
    
    @Override
    public String toString() {
        return getName() + " " + register + " " + value;
    }
}
