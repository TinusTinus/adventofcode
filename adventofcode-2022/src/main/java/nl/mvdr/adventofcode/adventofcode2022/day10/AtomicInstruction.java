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
     * @param x starting value of the x register
     * @return next value of the x register
     */
    int perform(int x);
}
