package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Parameter mode.
 *
 * @author Martijn van de Rijdt
 */
enum ParameterMode {

    /**
     * Causes the parameter to be interpreted as a position - if the parameter is
     * 50, its value is the value stored at address 50 in memory.
     */
    POSITION(0),

    /**
     * In immediate mode, a parameter is interpreted as a value - if the parameter
     * is 50, its value is simply 50.
     */
    IMMEDIATE(1);
    
    private final int code;

    /**
     * Gets a parameter value.
     * 
     * @param code encoding of a parameter mode within an opcode value's digits
     * @return paramter mode
     */
    static ParameterMode of(int code) {
        return Stream.of(values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unknown parameter mode code: " + code));
    }
    
    /**
     * Constructor.
     * 
     * @param code encoding of this mode within an opcode value's digits
     */
    ParameterMode(int code) {
        this.code = code;
    }
}
