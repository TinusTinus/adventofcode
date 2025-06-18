package nl.mvdr.adventofcode.adventofcode2016.assembunny;

record IntegerExpression(int value) implements Expression {
    
    @Override
    public int evaluate(Program program) {
        return value;
    }
}
