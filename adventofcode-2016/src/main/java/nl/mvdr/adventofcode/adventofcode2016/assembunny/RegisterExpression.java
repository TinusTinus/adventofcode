package nl.mvdr.adventofcode.adventofcode2016.assembunny;

record RegisterExpression(Register register) implements Expression {

    @Override
    public int evaluate(State state) {
        return state.registers().get(register).intValue();
    }

}
