package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/21">Monkey Math</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart2 extends MonkeyMath {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMathPart2.class);

    @Override
    protected Map<String, Value> parse(Stream<String> lines) {
        var result = super.parse(lines);
        
        // Overwrite the special "root" value.
        ExpressionValue rootValue = (ExpressionValue) result.get("root");
        rootValue = new ExpressionValue(rootValue.lhs(), Operator.MATCH, rootValue.rhs());
        result.put("root", rootValue);
        
        // Overwrite the special "humn" value.
        result.put("humn", SearchedValue.INSTANCE);
        
        return result;
    }
    
    @Override
    protected long solve(Map<String, Value> values) {
        // invariant: source evaluates to target
        Value source = values.get("root");
        long target = 0L; // root is a match expression
        
        while (source instanceof ExpressionValue sourceExpression) {
            if (values.get(sourceExpression.lhs()) instanceof NumberValue lhs) {
                // Left-hand side value has already been calculated.
                // Determine the right-hand side value.
                target = sourceExpression.operator().findRhs(lhs, target);
                source = values.get(sourceExpression.rhs());
            } else if (values.get(sourceExpression.rhs()) instanceof NumberValue rhs) {
                // Right-hand side value has already been calculated.
                // Determine the left-hand side value.
                target = sourceExpression.operator().findLhs(rhs, target);
                source = values.get(sourceExpression.lhs());
            } else {
                throw new IllegalArgumentException();
            }
        }
        
        if (source != SearchedValue.INSTANCE) {
            throw new IllegalArgumentException();
        }
        return target;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyMathPart2();

        var result = instance.solve("input-day21-2022.txt");

        LOGGER.info(result);
    }
}
 