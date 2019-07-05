package nl.mvdr.adventofcode.adventofcode2018.opcode;

/**
 * Function to compute the output of an {@link Opcode}.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
interface Operation {
    /**
     * Computes the output for an {@link Opcode}.
     * 
     * @param a input parameter A; may contain an immediate value or a register number, depending on the OpCode
     * @param b input parameter A; may contain an immediate value or a register number, depending on the OpCode
     * @param registers register values before the operation
     * @return register values after the operation
     */
    int computeOutput(int a, int b, int[] registers);
}
