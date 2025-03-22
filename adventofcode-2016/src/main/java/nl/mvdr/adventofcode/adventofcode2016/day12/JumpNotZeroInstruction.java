package nl.mvdr.adventofcode.adventofcode2016.day12;

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
    public State execute(State state) {
        int offset = switch(x.evaluate(state)) {
            case 0 -> 1;
            default -> y.evaluate(state);
        };
        return new State(state.registers(), state.instructionPointer() + offset);
    }
}
