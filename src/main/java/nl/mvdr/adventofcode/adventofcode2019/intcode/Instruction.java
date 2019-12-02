package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * An instruction in the Intcode language.
 *
 * @author Martijn van de Rijdt
 */
enum Instruction {

    /**
     * This instruction adds together numbers read from two positions and stores the
     * result in a third position. The three integers immediately after the opcode
     * tell you these three positions - the first two indicate the positions from
     * which you should read the input values, and the third indicates the position
     * at which the output should be stored.
     * 
     * For example, if your Intcode computer encounters 1,10,20,30, it should read
     * the values at positions 10 and 20, add those values, and then overwrite the
     * value at position 30 with their sum.
     */
    ADD(1, program -> perform(program, (i, j) -> i + j)),

    /**
     * This instruction works exactly like {@link #ADD}, except it multiplies the
     * two inputs instead of adding them. Again, the three integers after the opcode
     * indicate where the inputs and outputs are, not their values.
     */
    MULTIPLY(2, program -> perform(program, (i, j) -> i * j)),

    /**
     * This instruction means that the program is finished and should immediately
     * halt.
     */
    HALT(99, program -> new Program(program.getIntegers(), program.getInstructionPointer(), true));

    private final int opcode;
    
    private final Function<Program, Program> operation;

    /**
     * Gets the instruction with the given opcode.
     * 
     * @param opcode opcode of an existing instruction
     * @return instruction
     */
    static Instruction of(int opcode) {
        return Stream.of(values())
                .filter(instruction -> instruction.opcode == opcode)
                .findFirst()
                .orElseThrow();
    }
    
    private static Program perform(Program program, IntBinaryOperator operator) {
        int index1 = program.getIntegers().get(program.getInstructionPointer() + 1).intValue();
        int index2 = program.getIntegers().get(program.getInstructionPointer() + 2).intValue();
        int index3 = program.getIntegers().get(program.getInstructionPointer() + 3).intValue();
        
        int newValue = operator.applyAsInt(program.getIntegers().get(index1).intValue(), program.getIntegers().get(index2).intValue());

        List<Integer> integers = new ArrayList<>(program.getIntegers());
        integers.set(index3, Integer.valueOf(newValue));
        
        return new Program(List.copyOf(integers), program.getInstructionPointer() + 4, false);
    }
    
    /**
     * Constructor.
     * 
     * @param opcode opcode of this instruction
     * @param operation the actual operation
     */
    Instruction(int opcode, Function<Program, Program> operation) {
        this.opcode = opcode;
        this.operation = operation;
    }
    
    /**
     * Executes this instruction on the given program.
     * 
     * @param program program
     * @return program state after execution of this instruction
     */
    Program execute(Program program) {
        return operation.apply(program);
    }
}
