package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.List;

record DecreaseInstruction(Expression x) implements Instruction {

    static DecreaseInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        return new DecreaseInstruction(parameters.getFirst());
    }
    
    @Override
    public State execute(State state) {
        return switch (x) {
            case RegisterExpression registerExpression -> state.setRegister(registerExpression.register(),
                    state.registers().get(registerExpression.register()).intValue() - 1);
            case IntegerExpression _ -> state;
        };
    }
    
    @Override
    public Instruction toggle() {
        return new IncreaseInstruction(x);
    }
}
