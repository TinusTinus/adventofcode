package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.ArrayList;
import java.util.List;

import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Container class for the state of an Intcode program and a value, which may
 * represent input or output for the program.
 *
 * @author Martijn van de Rijdt
 */
class ProgramAndValue {
    
    private final Program program;
    
    private final long value;

    /**
     * Constructor.
     * 
     * @param program Intcode program state
     * @param value the value, which may represent input or output for the program
     */
    ProgramAndValue(Program program, long value) {
        super();
        this.program = program;
        this.value = value;
    }
    
    /** @return program state */
    Program getProgram() {
        return program;
    }
    
    /** @return the value, which may represent input or output for the program */
    long getValue() {
        return value;
    }
    
    /**
     * Executes the next input instruction (offering this object's value as input)
     * and the next output instruction.
     * 
     * This assumes that the program interchangeably asks for input and output.
     * 
     * @return updated program state and the received output value
     */
    ProgramAndValue performInputAndOutput() {
        List<Long> outputValues = new ArrayList<>(1);
        
        Program result = program
                .withInput(() -> value)
                .withOutput(outputValues::add)
                .executeUntilNextInput()
                .executeInstruction()
                .executeUntilNextInput();
        
        if (outputValues.size() != 1) {
            throw new IllegalStateException("Unexpected output received: " + outputValues);
        }
        
        return new ProgramAndValue(result, outputValues.get(0).longValue());
    }
}
