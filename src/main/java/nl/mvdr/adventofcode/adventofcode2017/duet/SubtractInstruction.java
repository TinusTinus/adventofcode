package nl.mvdr.adventofcode.adventofcode2017.duet;

/**
 * The instruction sub X Y decreases register X by the value of Y.
 *
 * @author Martijn van de Rijdt
 */
class SubtractInstruction extends UpdateRegisterInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "sub";
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    SubtractInstruction(String register, String value) {
        super(register, value);
    }
    
    @Override
    long computeNewValue(long oldValue, State state) {
        return oldValue - getValue(state);
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
