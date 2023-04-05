package nl.mvdr.adventofcode.adventofcode2022.day10;

/**
 * No operation: has no effect.
 *
 * @author Martijn van de Rijdt
 */
class NoopInstruction implements AtomicInstruction {

    /**
     * Singleton instance.
     */
    static final NoopInstruction INSTANCE = new NoopInstruction();
    
    /**
     * Private contructor to prevent singleton instantiation.
     */
    private NoopInstruction() {
    }
    
    @Override
    public int perform(int x) {
        return x;
    }
    
    @Override
    public String toString() {
        return "NoopInstruction";
    }
}
