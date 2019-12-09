package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * An instruction in the Intcode language.
 *
 * @author Martijn van de Rijdt
 */
enum Instruction {

    /**
     * This instruction adds together numbers read from two positions and stores the
     * result in a third position.
     */
    ADD(1, 3, Program::add),

    /**
     * This instruction works exactly like {@link #ADD}, except it multiplies the
     * two inputs instead of adding them.
     */
    MULTIPLY(2, 3, Program::multiply),

    /**
     * Takes a single integer as input and saves it to the position given by its
     * only parameter. For example, the instruction 3,50 would take an input value
     * and store it at address 50.
     */
    INPUT(3, 1, Program::storeInput),

    /**
     * Opcode 4 outputs the value of its only parameter.
     */
    OUTPUT(4, 1, Program::output),

    /**
     * If the first parameter is non-zero, this instruction sets the instruction pointer to the
     * value from the second parameter. Otherwise, it does nothing.
     */
    JUMP_IF_TRUE(5, 2, Program::jumpIfTrue),
    
    /**
     * If the first parameter is zero, it sets the instruction pointer to the value
     * from the second parameter. Otherwise, it does nothing.
     */
    JUMP_IF_FALSE(6, 2, Program::jumpIfFalse),
    
    /**
     * If the first parameter is less than the second parameter, this instruction
     * stores 1 in the position given by the third parameter. Otherwise, it stores
     * 0.
     */
    LESS_THAN(7, 3, Program::isLessThan),

    /**
     * If the first parameter is equal to the second parameter, this instruction
     * stores 1 in the position given by the third parameter. Otherwise, it stores
     * 0.
     */
    EQUALS(8, 3, Program::equals),
    
    /**
     * Adjusts the relative base by the value of its only parameter. The relative
     * base increases (or decreases, if the value is negative) by the value of the
     * parameter.
     */
    RELATIVE_BASE_OFFSET(9, 1, Program::adjustRelativeBase),

    /**
     * This instruction means that the program is finished and should immediately
     * halt.
     */
    HALT(99, 0, Program::halt);

    private final int opcode;

    private final int parameterCount;

    private final BiFunction<Program, List<ParameterMode>, Program> operation;

    /**
     * Gets the instruction with the given opcode.
     * 
     * @param opcode opcode of an existing instruction
     * @return instruction
     */
    static Instruction of(int opcode) {
        return Stream.of(values()).filter(instruction -> instruction.opcode == opcode).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unknown opcode: " + opcode));
    }

    /**
     * Constructor.
     * 
     * @param opcode         opcode of this instruction
     * @param parameterCount the number of parameters this instruction requires
     * @param operation      the actual operation
     */
    Instruction(int opcode, int parameterCount, BiFunction<Program, List<ParameterMode>, Program> operation) {
        this.opcode = opcode;
        this.parameterCount = parameterCount;
        this.operation = operation;
    }

    /**
     * Executes this instruction on the given program.
     * 
     * @param program        program
     * @param parameterModes parameter modes
     * @return program state after execution of this instruction
     */
    Program execute(Program program, List<ParameterMode> parameterModes) {
        return operation.apply(program, parameterModes);
    }

    /** @return the number of parameters this instruction requires */
    int getParameterCount() {
        return parameterCount;
    }
}
