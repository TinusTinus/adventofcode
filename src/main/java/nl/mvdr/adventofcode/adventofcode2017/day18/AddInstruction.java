package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction add X Y increases register X by the value of Y.
 *
 * @author Martijn van de Rijdt
 */
class AddInstruction extends UpdateRegisterInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "add";
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    AddInstruction(String register, String value) {
        super(register, value);
    }
    
    @Override
    long computeNewValue(long oldValue, State state) {
        return oldValue + getValue(state);
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
