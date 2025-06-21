package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.function.IntConsumer;
import java.util.stream.Stream;

sealed interface Instruction permits CopyInstruction, IncreaseInstruction, DecreaseInstruction, JumpNotZeroInstruction, ToggleInstruction, OutInstruction {
    
    static Instruction parse(String stringRepresentation, IntConsumer outputHandler) {
        var parts = stringRepresentation.split(" ");
        
        var instructionString = parts[0];
        
        var parameters = Stream.of(parts)
                .skip(1L)
                .map(Expression::parse)
                .toList();
        
        return switch(instructionString) {
            case "cpy" -> CopyInstruction.withParameters(parameters);
            case "inc" -> IncreaseInstruction.withParameters(parameters);
            case "dec" -> DecreaseInstruction.withParameters(parameters);
            case "jnz" -> JumpNotZeroInstruction.withParameters(parameters);
            case "tgl" -> ToggleInstruction.withParameters(parameters);
            case "out" -> OutInstruction.withParameters(parameters, outputHandler);
            default -> throw new IllegalArgumentException("Unsupported instruction: " + stringRepresentation);
        };
    }
    
    Program execute(Program program);
    
    Instruction toggle();
}
