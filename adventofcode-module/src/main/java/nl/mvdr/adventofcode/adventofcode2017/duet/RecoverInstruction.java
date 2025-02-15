package nl.mvdr.adventofcode.adventofcode2017.duet;

/**
 * The instruction rcv X recovers the frequency of the last sound played,
 * but only when the value of X is not zero.
 * (If it is zero, the command does nothing.)
 *
 * @author Martijn van de Rijdt
 */
class RecoverInstruction implements Instruction {

    /** Keyword for this instruction. */
    static final String NAME = "rcv";
    
    private final String register;
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     */
    RecoverInstruction(String register) {
        super();
        this.register = register;
    }
    
    @Override
    public State execute(State startState) {
        long value = startState.getRegisterValue(register);
        
        State result = startState;
        if (value != 0) {
            result = result.recover();
        }
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }
    
    @Override
    public String toString() {
        return NAME + " " + register;
    }
}
