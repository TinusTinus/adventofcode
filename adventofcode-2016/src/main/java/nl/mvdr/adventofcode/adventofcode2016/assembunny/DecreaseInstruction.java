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
    public Program execute(Program program) {
        return switch (x) {
            case RegisterExpression registerExpression -> program.setRegister(registerExpression.register(),
                    program.registers().get(registerExpression.register()).intValue() - 1);
            case IntegerExpression _ -> program;
        };
    }
    
    @Override
    public Instruction toggle() {
        return new IncreaseInstruction(x);
    }
}
