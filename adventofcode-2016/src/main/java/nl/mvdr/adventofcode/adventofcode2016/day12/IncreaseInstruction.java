package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.List;

record IncreaseInstruction(Register x) implements Instruction {

    static IncreaseInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        var x = switch (parameters.getFirst()) {
            case RegisterExpression expression -> expression.register();
            default -> throw new IllegalArgumentException("Register expression expected, but got: " + parameters.getLast());
        };
        return new IncreaseInstruction(x);
    }
    
    @Override
    public State execute(State state) {
        return state.setRegister(x, state.registers().get(x).intValue() + 1);
    }
}
