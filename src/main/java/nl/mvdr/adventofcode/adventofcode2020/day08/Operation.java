package nl.mvdr.adventofcode.adventofcode2020.day08;

import java.util.stream.Stream;

/**
 * An operation in handheld console boot code.
 *
 * @author Martijn van de Rijdt
 */
enum Operation {
    /**
     * {@code acc} increases or decreases a single global value called the
     * <em>accumulator</em> by the value given in the argument. For example,
     * {@code acc +7} would increase the accumulator by 7. The accumulator starts at
     * {@code 0}. After an {@code acc} instruction, the instruction immediately
     * below it is executed next.
     */
    ACC {
        @Override
        ProgramState execute(ProgramState inputState, int argument) {
            return new ProgramState(inputState.instructionPointer() + 1, inputState.accumulator() + argument);
        }
    },
    
    /**
     * {@code jmp} <em>jumps</em> to a new instruction relative to itself. The next
     * instruction to execute is found using the argument as an offset from the
     * {@code jmp} instruction; for example, {@code jmp +2} would skip the next
     * instruction, {@code jmp +1} would continue to the instruction immediately
     * below it, and {@code jmp -20} would cause the instruction 20 lines above to
     * be executed next.
     */
    JMP {
        @Override
        ProgramState execute(ProgramState inputState, int argument) {
            return new ProgramState(inputState.instructionPointer() + argument, inputState.accumulator());
        }
    },
    
    /**
     * {@code nop} stands for <em>No OPeration</em> - it does nothing. The
     * instruction immediately below it is executed next.
     */
    NOP {
        @Override
        ProgramState execute(ProgramState inputState, int argument) {
            return new ProgramState(inputState.instructionPointer() + 1, inputState.accumulator());
        }
    };
    
    /**
     * Parses a string representation of an operation.
     * 
     * @param value string value, for example: "acc"
     * @return operation
     */
    static Operation fromString(String value) {
        return Stream.of(Operation.values())
                .filter(operation -> operation.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Executes this operation.
     * 
     * @param inputState program state before executing the operation
     * @param argument instruction argument
     * @return program state after executing the operation
     */
    abstract ProgramState execute(ProgramState inputState, int argument);
}
