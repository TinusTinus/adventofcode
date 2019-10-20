package nl.mvdr.adventofcode.adventofcode2017.duet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Queue;

/**
 * State during a program's execution.
 *
 * @author Martijn van de Rijdt
 */
public class State {
    
    private final Map<String, Long> registers;
    
    private final OptionalLong lastPlayedFrequency;
    private final OptionalLong recoveredFrequency;
    
    private final int instructionPointer;
    
    private final Queue<Long> source;
    private final Queue<Long> target;
    
    private final int timesSent;
    
    /** Constructor, for use when no communication between processes is needed. Creates the initial state. */
    public State() {
        this(Map.of(), OptionalLong.empty(), OptionalLong.empty(), 0, new LinkedList<>(), new LinkedList<>(), 0);
    }
    
    /**
     * Constructor. Creates the initial state.
     * 
     * @param programId program ID (0 or 1), to be stored in register "p"
     * @param source source queue, from which receive instructions should read data
     * @param target target queue, to which send instructions should send data
     */
    public State(int programId, Queue<Long> source, Queue<Long> target) {
        this(Map.of("p", Long.valueOf(programId)), OptionalLong.empty(), OptionalLong.empty(), 0, source, target, 0);
    }

    /**
     * Constructor.
     * 
     * @param registers register values
     * @param lastPlayedFrequency frequency of the last sound played on the sound card
     * @param recoveredFrequency frequency recovered by the last recover instruction
     * @param instructionPointer inscruction pointer
     * @param source source queue, from which receive instructions should read data
     * @param target target queue, to which send instructions should send data
     */
    private State(Map<String, Long> registers, OptionalLong lastPlayedFrequency, OptionalLong recoveredFrequency,
            int instructionPointer, Queue<Long> source, Queue<Long> target, int timesSent) {
        super();
        this.registers = registers;
        this.lastPlayedFrequency = lastPlayedFrequency;
        this.recoveredFrequency = recoveredFrequency;
        this.instructionPointer = instructionPointer;
        this.source = source;
        this.target = target;
        this.timesSent = timesSent;
    }

    /**
     * Returns the value of a register.
     *  
     * @param register name of the register to inspect
     * @return register value
     */
    public long getRegisterValue(String register) {
        if (register.length() != 1 || !Character.isLetter(register.charAt(0))) {
            throw new IllegalArgumentException(register);
        }
        
        return registers.getOrDefault(register, Long.valueOf(0L)).longValue();
    }
    
    public OptionalLong getRecoveredFrequency() {
        return recoveredFrequency;
    }
    
    public int getInstructionPointer() {
        return instructionPointer;
    }
    
    public int getTimesSent() {
        return timesSent;
    }
    
    /**
     * Returns a new state, with an updated register value.
     * 
     * @param registerName register to update
     * @param value value to store in the register
     * @return updated state
     */
    State updateRegister(String registerName, long value) {
        Map<String, Long> newRegisters = new HashMap<>(registers);
        newRegisters.put(registerName, Long.valueOf(value));
        
        return new State(newRegisters, lastPlayedFrequency, recoveredFrequency, instructionPointer, source, target, timesSent);
    }
    
    /**
     * Returns a new state after playing a sound.
     * 
     * @param frequency frequency to play
     * @return updated state
     */
    State play(long frequency) {
        return new State(registers, OptionalLong.of(frequency), recoveredFrequency, instructionPointer, source, target, timesSent);
    }
    
    /**
     * Returns a new state with an updated instruction pointer.
     * 
     * @param value new instruction pointer value
     * @return updated state
     */
    State updateInstructionPointer(int value) {
        return new State(registers, lastPlayedFrequency, recoveredFrequency, value, source, target, timesSent);
    }
    
    /**
     * Returns a new state after recovery of the last played sound.
     * 
     * @return updated state
     */
    State recover() {
        return new State(registers, lastPlayedFrequency, lastPlayedFrequency, instructionPointer, source, target, timesSent);
    }
    
    /** @return whether the program can currently receive a value; if not its execution should block */
    boolean canReceive() {
        return !source.isEmpty();
    }
    
    /**
     * Returns a new state after receiving a value.
     * 
     * @param registerName register in which to store the received value
     * @return updated state
     */
    State receive(String registerName) {
        long value = source.poll().longValue();
        return updateRegister(registerName, value);
    }
    
    /**
     * Returns a new state after sending a value.
     * 
     * @param value value to send
     * @return updated state
     */
    State send(long value) {
        target.offer(Long.valueOf(value));
        return new State(registers, lastPlayedFrequency, recoveredFrequency, instructionPointer, source, target, timesSent + 1);
    }

    @Override
    public String toString() {
        return "State [registers=" + registers
                + ", lastPlayedFrequency=" + lastPlayedFrequency
                + ", recoveredFrequency=" + recoveredFrequency
                + ", instructionPointer=" + instructionPointer
                + ", source=" + source
                + ", timesSent=" + timesSent
                + "]";
    }
}
