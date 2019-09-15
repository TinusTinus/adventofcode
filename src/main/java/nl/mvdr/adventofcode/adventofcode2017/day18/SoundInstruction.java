package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction snd X plays a sound with a frequency equal to the value of X.
 *
 * @author Martijn van de Rijdt
 */
class SoundInstruction implements Instruction {

    /** Keyword for this instruction. */
    static final String NAME = "snd";
    
    private final String register;
    
    /**
     * Constructor.
     * 
     * @param register name of the register containing the frequency
     */
    SoundInstruction(String register) {
        super();
        this.register = register;
    }
    
    @Override
    public State execute(State startState) {
        long frequency = startState.getRegisterValue(register);
        
        State result = startState.play(frequency);
        result = result.updateInstructionPointer(result.getInstructionPointer() + 1);
        return result;
    }
    
    @Override
    public String toString() {
        return NAME + " " + register;
    }
}
