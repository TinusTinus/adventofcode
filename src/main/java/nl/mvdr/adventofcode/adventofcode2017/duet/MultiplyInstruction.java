package nl.mvdr.adventofcode.adventofcode2017.duet;

/**
 * The instruction mul X Y sets register X to the result of
 * multiplying the value contained in register X by the value of Y.
 *
 * @author Martijn van de Rijdt
 */
class MultiplyInstruction extends UpdateRegisterInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "mul";
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    MultiplyInstruction(String register, String value) {
        super(register, value);
    }
    
    @Override
    long computeNewValue(long oldValue, State state) {
        return oldValue * getValue(state);
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
