package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.List;

record CopyInstruction(Expression x, Register y) implements Instruction {
    
    static CopyInstruction withParameters(List<Expression> parameters) {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("Unexpected parameters: " + parameters);
        }
        var x = parameters.getFirst();
        var y = switch (parameters.getLast()) {
            case RegisterExpression expression -> expression.register();
            default -> throw new IllegalArgumentException("Register expression expected, but got: " + parameters.getLast());
        };
        
        return new CopyInstruction(x, y);
    }
    
    
    @Override
    public State execute(State state) {
        return state.setRegister(y, x.evaluate(state));
    }
}
