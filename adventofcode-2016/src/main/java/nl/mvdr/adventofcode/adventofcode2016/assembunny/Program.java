package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record Program(List<Instruction> instructions, Map<Register, Integer> registers, int instructionPointer) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    public static Program parse(Stream<String> input, Map<Register, Integer> registers) {
        return parse(input, registers, output -> LOGGER.info("{}", output));
    }
    
    public static Program parse(Stream<String> input, Map<Register, Integer> registers, IntConsumer outputHandler) {
        var instructions = input.map(line -> Instruction.parse(line, outputHandler)).toList();
        return new Program(instructions, registers, 0);
    }
    
    public Program execute() {
        return executeWhile(program -> program.instructionPointer() < program.instructions.size());
    }

    public Program executeWhile(BooleanSupplier guard) {
        return executeWhile(_ -> guard.getAsBoolean());
    }
    
    public Program executeWhile(Predicate<Program> guard) {
        Program program = this;
        while (guard.test(program)) {
            LOGGER.debug("{}", program);
            var instruction = program.instructions().get(program.instructionPointer());
            program = instruction.execute(program);
        }
        return program;
    }
    
    Program setRegister(Register register, int value) {
        Map<Register, Integer> newRegisters = new HashMap<>(registers);
        newRegisters.put(register, Integer.valueOf(value));
        return new Program(instructions, newRegisters, instructionPointer + 1);
    }
    
    Program setInstructionPointer(int newInstructionPointerValue) {
        return new Program(instructions, registers, newInstructionPointerValue);
    }
}
