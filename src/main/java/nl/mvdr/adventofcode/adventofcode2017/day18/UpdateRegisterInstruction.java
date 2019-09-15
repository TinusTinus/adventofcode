package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * Instruction which updates a register value.
 *
 * @author Martijn van de Rijdt
 */
abstract class UpdateRegisterInstruction extends ValueParameterInstruction {
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    UpdateRegisterInstruction(String register, String value) {
        super(register, value);
    }
    
    @Override
    public State execute(State startState) {
        int oldValue = startState.getRegisterValue(getRegister());
        
        State result = startState.updateRegister(getRegister(), computeNewValue(oldValue, startState));
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
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
}
