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
    IMMEDIATE(1),

    /**
     * Parameters in mode 2, relative mode, behave very similarly to parameters in
     * {@link #POSITION} mode: the parameter is interpreted as a position. Like
     * position mode, parameters in relative mode can be read from or written to.
     * 
     * The important difference is that relative mode parameters don't count from
     * address 0. Instead, they count from a value called the relative base. The
     * relative base starts at 0.
     * 
     * The address a relative mode parameter refers to is itself plus the current
     * relative base. When the relative base is 0, relative mode parameters and
     * position mode parameters with the same value refer to the same address.
     * 
     * For example, given a relative base of 50, a relative mode parameter of -7
     * refers to memory address 50 + -7 = 43.
     */
    RELATIVE(2);
    
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
