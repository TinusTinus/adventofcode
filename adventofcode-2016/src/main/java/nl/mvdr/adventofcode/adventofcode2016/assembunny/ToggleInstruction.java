package nl.mvdr.adventofcode.adventofcode2016.assembunny;

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
        // TODO Auto-generated method stub!
        return null;
    }
    
    @Override
    public Instruction toggle() {
        return new IncreaseInstruction(x);
    }
}
