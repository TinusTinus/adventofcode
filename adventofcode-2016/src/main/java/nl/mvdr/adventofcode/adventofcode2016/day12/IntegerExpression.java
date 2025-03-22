package nl.mvdr.adventofcode.adventofcode2016.day12;

record IntegerExpression(int value) implements Expression {
    
    @Override
    public int evaluate(State state) {
        return value;
    }
}
