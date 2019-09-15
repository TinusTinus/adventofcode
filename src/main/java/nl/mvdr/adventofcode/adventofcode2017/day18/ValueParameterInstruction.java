package nl.mvdr.adventofcode.adventofcode2017.day18;

/**
 * Instruction with a value parameter.
 *
 * @author Martijn van de Rijdt
 */
abstract class ValueParameterInstruction implements Instruction {

    private final String register;
    private final String value;
    
    /**
     * Constructor.
     * 
     * @param register name of the register
     * @param value value
     */
    ValueParameterInstruction(String register, String value) {
        super();
        this.register = register;
        this.value = value;
    }
    
    /**
     * Gets the integer value for the second parameter for this instruction.
     * 
     * @param state current state
     * @return value
     */
    int getValue(State state) {
        int result;
        try {
            result = Integer.parseInt(value);
        } catch (@SuppressWarnings("unused") NumberFormatException e) {
            result = state.getRegisterValue(value);
        }
        return result;
    }
    
    String getRegister() {
        return register;
    }
    
    /** @return keyword for the specific instruction */
    abstract String getName();
    
    @Override
    public String toString() {
        return getName() + " " + register + " " + value;
    }
}
