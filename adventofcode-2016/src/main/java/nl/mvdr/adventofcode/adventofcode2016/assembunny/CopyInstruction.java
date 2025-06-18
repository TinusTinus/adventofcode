package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.List;

record CopyInstruction(Expression x, Expression y) implements Instruction {
    
    static CopyInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        var x = parameters.getFirst();
        var y = parameters.getLast();
        
        return new CopyInstruction(x, y);
    }
    
    
    @Override
    public State execute(State state) {
        return switch (y) {
            case RegisterExpression registerExpression -> state.setRegister(registerExpression.register(), x.evaluate(state));
            case IntegerExpression _ -> state; // skip
        };
    }
    
    @Override
    public Instruction toggle() {
        return new JumpNotZeroInstruction(x, y);
    }
}
