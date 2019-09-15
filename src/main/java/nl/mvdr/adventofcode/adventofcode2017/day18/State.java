package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalLong;

/**
 * State during a program's execution.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private final Map<String, Long> registers;
    
    private final OptionalLong lastPlayedFrequency;
    private final OptionalLong recoveredFrequency;
    
    private final int instructionPointer;
    
    /** Constructor. Creates the initial state. */
    State() {
        super();
        this.registers = Map.of();
        this.lastPlayedFrequency = OptionalLong.empty();
        this.recoveredFrequency = OptionalLong.empty();
        this.instructionPointer = 0;
    }

    /**
     * Constructor.
     * 
     * @param registers register values
     * @param lastPlayedFrequency frequency of the last sound played on the sound card
     * @param instructionPointer inscruction pointer
     */
    private State(Map<String, Long> registers, OptionalLong lastPlayedFrequency, OptionalLong recoveredFrequency, int instructionPointer) {
        super();
        this.registers = registers;
        this.lastPlayedFrequency = lastPlayedFrequency;
        this.recoveredFrequency = recoveredFrequency;
        this.instructionPointer = instructionPointer;
    }

    /**
     * Returns the value of a register.
     *  
     * @param register name of the register to inspect
     * @return register value
     */
    long getRegisterValue(String register) {
        if (register.length() != 1 || !Character.isLetter(register.charAt(0))) {
            throw new IllegalArgumentException(register);
        }
        
        return registers.getOrDefault(register, Long.valueOf(0L)).intValue();
    }
    
    OptionalLong getRecoveredFrequency() {
        return recoveredFrequency;
    }
    
    int getInstructionPointer() {
        return instructionPointer;
    }
    
    State updateRegister(String registerName, long value) {
        Map<String, Long> newRegisters = new HashMap<>(registers);
        newRegisters.put(registerName, Long.valueOf(value));
        
        return new State(newRegisters, lastPlayedFrequency, recoveredFrequency, instructionPointer);
    }
    
    State play(long frequency) {
        return new State(registers, OptionalLong.of(frequency), recoveredFrequency, instructionPointer);
    }
    
    State updateInstructionPointer(int value) {
        return new State(registers, lastPlayedFrequency, recoveredFrequency, value);
    }
    
    State recover() {
        return new State(registers, lastPlayedFrequency, lastPlayedFrequency, instructionPointer);
    }

    @Override
    public String toString() {
        return "State [registers=" + registers
                + ", lastPlayedFrequency=" + lastPlayedFrequency
                + ", recoveredFrequency=" + recoveredFrequency
                + ", instructionPointer=" + instructionPointer
                + "]";
    }
    
    
}
