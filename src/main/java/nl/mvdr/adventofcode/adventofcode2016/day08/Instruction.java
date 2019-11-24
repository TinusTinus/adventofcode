package nl.mvdr.adventofcode.adventofcode2016.day08;

/**
 * Representation of an instruction.
 *
 * @author Martijn van de Rijdt
 */
class Instruction {
    
    private final Operation operation;
    private final int a;
    private final int b;

    static Instruction parse(String string) {
        
        // TODO
        
        return null;
    }
    
    /**
     * Constructor.
     * 
     * @param operation operation
     * @param a first parameter
     * @param b second parameter
     */
    private Instruction(Operation operation, int a, int b) {
        super();
        this.operation = operation;
        this.a = a;
        this.b = b;
    }
    
    /**
     * Executes this instruction.
     * 
     * @param input screen before execution of the instruction
     * @return updated screen
     */
    Screen execute(Screen input) {
        return operation.execute(input, a, b);
    }
}
