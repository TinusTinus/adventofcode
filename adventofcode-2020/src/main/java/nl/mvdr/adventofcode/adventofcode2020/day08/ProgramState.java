package nl.mvdr.adventofcode.adventofcode2020.day08;

/**
 * State of the exectution of a handheld console boot code program.
 *
 * @author Martijn van de Rijdt
 */
record ProgramState(int instructionPointer, int accumulator) {
    
    /** The initial state of a program execution. */
    static final ProgramState INITIAL = new ProgramState(0, 0);
}
