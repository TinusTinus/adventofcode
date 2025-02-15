package nl.mvdr.adventofcode.adventofcode2017.duet;

/**
 * A jump instruction j?? X Y jumps with an offset of the value of Y, but only if
 * a certain condition is met. (An offset of 2 skips the next
 * instruction, an offset of -1 jumps to the previous instruction, and so on.)
 *
 * @author Martijn van de Rijdt
 */
abstract class JumpInstruction implements Instruction {

    private final String firstValue;
    private final String secondValue;
    
    /**
     * Constructor.
     * 
     * @param firstValue first value, which determines whether to jump; can be a register name or numeric
     * @param secondValue second value, which determines the jump offset; can be a register name or numeric
     */
    JumpInstruction(String firstValue, String secondValue) {
        super();
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    
    @Override
    public State execute(State startState) {
        long offset;
        if (performJump(Instruction.getValue(firstValue, startState))) {
            offset = Instruction.getValue(secondValue, startState);
        } else {
            offset = 1;
        }
        long newInstructionPointer = startState.getInstructionPointer() + offset;
        return startState.updateInstructionPointer((int)newInstructionPointer);
    }
    
    /**
     * Evaluates the jump condition.
     * 
     * @param value first value
     * @return whether to execute the jump
     */
    abstract boolean performJump(long value);
    
    /** @return name of the concrete instruction */
    abstract String getName();

    @Override
    public String toString() {
        return getName() + " " + firstValue + " " + secondValue;
    }
}
