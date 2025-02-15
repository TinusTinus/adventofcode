package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.util.List;

/**
 * A callback function which is called by {@link Program} right before executing an instruction.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface ProgramExecutionCallback {
    /**
     * Callback function.
     * 
     * @param registers current register values
     * @param instructionPointer current instruction pointer value
     * @return whether the program should proceed by executing the next instruction
     */
    boolean continueExecution(List<Integer> registers, int instructionPointer);
}
