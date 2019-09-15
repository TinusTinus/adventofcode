package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction set X Y sets register X to the value of Y.
 *
 * @author Martijn van de Rijdt
 */
class SetInstruction extends UpdateRegisterInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "set";
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    SetInstruction(String register, String value) {
        super(register, value);
    }
    
    @Override
    long computeNewValue(long oldValue, State state) {
        return getValue(state);
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
