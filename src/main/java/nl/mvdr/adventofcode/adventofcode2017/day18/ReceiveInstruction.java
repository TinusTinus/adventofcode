package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction rcv X receives the next value and stores it in register X.
 *
 * @author Martijn van de Rijdt
 */
class ReceiveInstruction implements Instruction {

    /** Keyword for this instruction. */
    static final String NAME = "rcv";
    
    private final String register;
    
    /**
     * Constructor.
     * 
     * @param register name of the register in which to store the received value
     */
    ReceiveInstruction(String register) {
        super();
        this.register = register;
    }

    @Override
    public boolean canProceed(State state) {
        return state.canReceive();
    }
    
    @Override
    public State execute(State startState) {
        State result = startState.receive(register);
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }
    
    @Override
    public String toString() {
        return NAME + " " + register;
    }
}
