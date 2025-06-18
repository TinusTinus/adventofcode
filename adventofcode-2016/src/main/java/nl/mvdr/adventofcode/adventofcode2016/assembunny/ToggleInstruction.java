package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.ArrayList;
import java.util.List;

record ToggleInstruction(Expression x) implements Instruction {

    static ToggleInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        return new ToggleInstruction(parameters.getFirst());
    }
    
    @Override
    public Program execute(Program program) {
        int offset = x.evaluate(program);
        var instructionIndex = program.instructionPointer() + offset;
        
        List<Instruction> newInstructions = new ArrayList<>(program.instructions());
        if (0 <= instructionIndex && instructionIndex < program.instructions().size()) {
            newInstructions.set(instructionIndex, program.instructions().get(instructionIndex).toggle());
        }
        return new Program(newInstructions, program.registers(), program.instructionPointer() + 1);
    }
    
    @Override
    public Instruction toggle() {
        return new IncreaseInstruction(x);
    }
}
