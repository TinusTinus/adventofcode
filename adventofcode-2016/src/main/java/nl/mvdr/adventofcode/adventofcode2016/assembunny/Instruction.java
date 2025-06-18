package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.stream.Stream;

sealed interface Instruction permits CopyInstruction, IncreaseInstruction, DecreaseInstruction, JumpNotZeroInstruction, ToggleInstruction {
    
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
            case "tgl" -> ToggleInstruction.withParameters(parameters);
            default -> throw new IllegalArgumentException("Unsupported instruction: " + stringRepresentation);
        };
    }
    
    Program execute(Program program);
    
    Instruction toggle();
}
