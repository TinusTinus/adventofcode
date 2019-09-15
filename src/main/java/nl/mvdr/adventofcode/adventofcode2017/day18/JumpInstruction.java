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
    
    private final String firstValue;
    private final String secondValue;
    
    /**
     * Constructor.
     * 
     * @param firstValue first value, which determines whether or not to jump; can be a register name or numeric
     * @param secondValue second value, which determines the jump offset; can be a register name or numeric
     */
    JumpInstruction(String firstValue, String secondValue) {
        super();
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    
    @Override
    public State execute(State startState) {
        int offset;
        if (getValue(firstValue, startState) <= 0) {
            offset = 1;
        } else {
            offset = getValue(secondValue, startState);
        }
        int newInstructionPointer = startState.getInstructionPointer() + offset;
        return startState.updateInstructionPointer(newInstructionPointer);
    }

    private int getValue(String value, State state) {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (@SuppressWarnings("unused") NumberFormatException e) {
            result = state.getRegisterValue(value);
        }
        return result;
    }
    
    @Override
    public String toString() {
        return NAME + " " + firstValue + " " + secondValue;
    }
}
