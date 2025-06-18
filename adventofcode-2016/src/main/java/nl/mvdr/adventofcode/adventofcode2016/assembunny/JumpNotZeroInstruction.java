package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.List;

record JumpNotZeroInstruction(Expression x, Expression y) implements Instruction {

    static JumpNotZeroInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        var x = parameters.getFirst();
        var y = parameters.getLast();
        return new JumpNotZeroInstruction(x, y);
    }
    
    @Override
    public Program execute(Program program) {
        int offset = switch(x.evaluate(program)) {
            case 0 -> 1;
            default -> y.evaluate(program);
        };
        return new Program(program.instructions(), program.registers(), program.instructionPointer() + offset);
    }
    
    @Override
    public Instruction toggle() {
        return new CopyInstruction(x, y);
    }
}
