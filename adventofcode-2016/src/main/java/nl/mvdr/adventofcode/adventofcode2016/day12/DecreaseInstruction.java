package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.List;

record DecreaseInstruction(Register x) implements Instruction {

    static DecreaseInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        Register x;
        if (parameters.getFirst() instanceof RegisterExpression xExpression) {
            x = xExpression.register();
        } else {
            throw new IllegalArgumentException("Register expression expected, but got: " + parameters.getLast());
        }
        return new DecreaseInstruction(x);
    }
    
    @Override
    public State execute(State state) {
        return state.setRegister(x, state.registers().get(x).intValue() - 1);
    }
}
