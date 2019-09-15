package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction jgz X Y jumps with an offset of the value of Y, but only if
 * the value of X is greater than zero. (An offset of 2 skips the next
 * instruction, an offset of -1 jumps to the previous instruction, and so on.)
 *
 * @author Martijn van de Rijdt
 */
class JumpInstruction extends ValueParameterInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "jgz";
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    JumpInstruction(String register, String value) {
        super(register, value);
    }
    
    @Override
    public State execute(State startState) {
        int offset;
        if (startState.getRegisterValue(getRegister()) <= 0) {
            offset = 1;
        } else {
            offset = getValue(startState);
        }
        int newInstructionPointer = startState.getInstructionPointer() + offset;
        return startState.updateInstructionPointer(newInstructionPointer);
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
