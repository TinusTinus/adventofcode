package nl.mvdr.adventofcode.adventofcode2022.day10;

/**
 * An instruction to perform.
 *
 * @author Martijn van de Rijdt
 */
interface Instruction {
    /**
     * Performs this instruction.
     * 
     * @param cpu start state of the cpu
     * @return next state of the cpu
     */
    Cpu perform(Cpu cpu);
}
