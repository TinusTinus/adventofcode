package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.stream.Stream;

sealed interface Instruction permits CopyInstruction, IncreaseInstruction, DecreaseInstruction, JumpNotZeroInstruction {
    
    static Instruction parse(String stringRepresentation) {
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
            default -> throw new IllegalArgumentException("Unsupported instruction: " + stringRepresentation);
        };
    }
    
    State execute(State state);
}
