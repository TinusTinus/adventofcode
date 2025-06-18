package nl.mvdr.adventofcode.adventofcode2016.assembunny;

record RegisterExpression(Register register) implements Expression {

    @Override
    public int evaluate(Program program) {
        return program.registers().get(register).intValue();
    }

}
