package nl.mvdr.adventofcode.adventofcode2022.day10;

/**
 * TODO javadoc
 *
 * @author Martijn van de Rijdt
 */
public class NoopInstruction implements Instruction {

    @Override
    public Cpu perform(Cpu cpu) {
        return new Cpu(cpu.cycleNumber() + 1, cpu.x());
    }

}
