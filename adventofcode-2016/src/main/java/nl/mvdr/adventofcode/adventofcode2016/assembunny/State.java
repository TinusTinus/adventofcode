package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record State(Map<Register, Integer> registers, int instructionPointer) {
    public State() {
        this(Stream.of(Register.values())
                .collect(Collectors.toMap(Function.identity(), _ -> Integer.valueOf(0))), 0);
    }
    
    public State withRegister(Register register, int value) {
        Map<Register, Integer> newRegisters = new HashMap<>(registers);
        newRegisters.put(register, Integer.valueOf(value));
        return new State(newRegisters, instructionPointer + 1);
    }
    
    public State withInstructionPointer(int newInstructionPointerValue) {
        return new State(registers, newInstructionPointerValue);
    }
}
