package nl.mvdr.adventofcode.adventofcode2022.day10;

/**
 * An atomic instruction for the central processing unit.
 * 
 * An atomic instruction takes exactly one cycle.
 *
 * @author Martijn van de Rijdt
 */
interface AtomicInstruction {
    /**
     * Performs this instruction.
     * 
     * @param cpu start state of the cpu
     * @return next state of the cpu
     */
    Cpu perform(Cpu cpu);
}
