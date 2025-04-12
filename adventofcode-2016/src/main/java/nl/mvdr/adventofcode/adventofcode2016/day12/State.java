package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record State(Map<Register, Integer> registers, int instructionPointer) {
    State() {
        this(Stream.of(Register.values())
                .collect(Collectors.toMap(Function.identity(), _ -> Integer.valueOf(0))), 0);
    }
    
    State setRegister(Register register, int value) {
        Map<Register, Integer> newRegisters = new HashMap<>(registers);
        newRegisters.put(register, Integer.valueOf(value));
        return new State(newRegisters, instructionPointer + 1);
    }
    
    State setInstructionPointer(int newInstructionPointerValue) {
        return new State(registers, newInstructionPointerValue);
    }
}
