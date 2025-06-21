package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.List;
import java.util.function.IntConsumer;

record OutInstruction(Expression x, IntConsumer outputHandler) implements Instruction {

    static OutInstruction withParameters(List<Expression> parameters, IntConsumer outputHandler) {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        return new OutInstruction(parameters.getFirst(), outputHandler);
    }
    
    @Override
    public Program execute(Program program) {
        outputHandler.accept(x.evaluate(program));
        return program.setInstructionPointer(program.instructionPointer() + 1);
    }
    
    @Override
    public Instruction toggle() {
        return new IncreaseInstruction(x);
    }
}
