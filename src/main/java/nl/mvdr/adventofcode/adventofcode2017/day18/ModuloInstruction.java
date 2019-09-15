package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * The instruction mod X Y sets register X to 
 * the remainder of dividing the value contained in register X by the value of Y
 * (that is, it sets X to the result of X modulo Y).
 *
 * @author Martijn van de Rijdt
 */
class ModuloInstruction extends UpdateRegisterInstruction {

    /** Keyword for this instruction. */
    static final String NAME = "mod";
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    ModuloInstruction(String register, int value) {
        super(register, value);
    }
    
    @Override
    int computeNewValue(int oldValue) {
        return oldValue % getValue();
    }
    
    @Override
    String getName() {
        return NAME;
    }
}
