package nl.mvdr.adventofcode.adventofcode2017.day18;

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
    MultiplyInstruction(String register, int value) {
        super(register, value);
    }
    
    @Override
    int computeNewValue(int oldValue) {
        return oldValue * getValue();
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
