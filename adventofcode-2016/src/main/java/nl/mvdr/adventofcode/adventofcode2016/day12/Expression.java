package nl.mvdr.adventofcode.adventofcode2016.day12;

sealed interface Expression permits IntegerExpression, RegisterExpression {
    
    static Expression parse(String stringRepresentation) {
        return Register.of(stringRepresentation)
                .map(RegisterExpression::new)
                .map(Expression.class::cast)
                .orElseGet(() -> new IntegerExpression(Integer.parseInt(stringRepresentation)));
    }
    
    int evaluate(State state);
}
