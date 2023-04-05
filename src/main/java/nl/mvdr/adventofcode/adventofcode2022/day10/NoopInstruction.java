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
    public Cpu perform(Cpu cpu) {
        return new Cpu(cpu.cycleNumber() + 1, cpu.x());
    }
    
    @Override
    public String toString() {
        return "NoopInstruction";
    }
}
