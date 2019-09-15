package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

/**
 * State during a program's execution.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private final Map<String, Integer> registers;
    
    private final OptionalInt lastPlayedFrequency;
    private final OptionalInt recoveredFrequency;
    
    private final int instructionPointer;
    
    /** Constructor. Creates the initial state. */
    State() {
        super();
        this.registers = Map.of();
        this.lastPlayedFrequency = OptionalInt.empty();
        this.recoveredFrequency = OptionalInt.empty();
        this.instructionPointer = 0;
    }

    /**
     * Constructor.
     * 
     * @param registers register values
     * @param lastPlayedFrequency frequency of the last sound played on the sound card
     * @param instructionPointer inscruction pointer
     */
    private State(Map<String, Integer> registers, OptionalInt lastPlayedFrequency, OptionalInt recoveredFrequency, int instructionPointer) {
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
    int getRegisterValue(String register) {
        if (register.length() != 1 || !Character.isLetter(register.charAt(0))) {
            throw new IllegalArgumentException(register);
        }
        
        return registers.getOrDefault(register, Integer.valueOf(0)).intValue();
    }
    
    OptionalInt getRecoveredFrequency() {
        return recoveredFrequency;
    }
    
    int getInstructionPointer() {
        return instructionPointer;
    }
    
    State updateRegister(String registerName, int value) {
        Map<String, Integer> newRegisters = new HashMap<>(registers);
        newRegisters.put(registerName, Integer.valueOf(value));
        
        return new State(newRegisters, lastPlayedFrequency, recoveredFrequency, instructionPointer);
    }
    
    State play(int frequency) {
        return new State(registers, OptionalInt.of(frequency), recoveredFrequency, instructionPointer);
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
