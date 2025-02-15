package nl.mvdr.adventofcode.adventofcode2017.duet;

/**
 * The instruction snd X sends the value of X to a target program.
 *
 * @author Martijn van de Rijdt
 */
public class SendInstruction implements Instruction {

    /** Keyword for this instruction. */
    static final String NAME = "snd";
    
    /** Value to send. */
    private final String value;
    
    /**
     * Constructor.
     * 
     * @param value value to send
     */
    SendInstruction(String value) {
        super();
        this.value = value;
    }
    
    @Override
    public State execute(State startState) {
        State result = startState.send(Instruction.getValue(value, startState));
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }
    
    @Override
    public String toString() {
        return NAME + " " + value;
    }
}
