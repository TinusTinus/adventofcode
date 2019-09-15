package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction jgz X Y jumps with an offset of the value of Y, but only if
 * the value of X is greater than zero. (An offset of 2 skips the next
 * instruction, an offset of -1 jumps to the previous instruction, and so on.)
 *
 * @author Martijn van de Rijdt
 */
class JumpInstruction implements Instruction {

    /** Keyword for this instruction. */
    static final String NAME = "jgz";
    
    private final String register;
    private final int value;
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    JumpInstruction(String register, int value) {
        super();
        this.register = register;
        this.value = value;
    }
    
    @Override
    public State execute(State startState) {
        int offset;
        if (startState.getRegisterValue(register) <= 0) {
            offset = 1;
        } else {
            offset = value;
        }
        int newInstructionPointer = startState.getInstructionPointer() + offset;
        return startState.updateInstructionPointer(newInstructionPointer);
    }

    @Override
    public String toString() {
        return NAME + " " + register + " " + value;
    }
}
