package nl.mvdr.adventofcode.adventofcode2017.duet;

/**
 * The instruction jgz X Y jumps with an offset of the value of Y, but only if
 * the value of X is greater than zero. (An offset of 2 skips the next
 * instruction, an offset of -1 jumps to the previous instruction, and so on.)
 *
 * @author Martijn van de Rijdt
 */
class JumpGreaterThanZeroInstruction extends JumpInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "jgz";
    
    /**
     * Constructor.
     * 
     * @param firstValue first value, which determines whether or not to jump; can be a register name or numeric
     * @param secondValue second value, which determines the jump offset; can be a register name or numeric
     */
    JumpGreaterThanZeroInstruction(String firstValue, String secondValue) {
        super(firstValue, secondValue);
    }
    
    @Override
    boolean performJump(long value) {
        return 0 < value;
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
