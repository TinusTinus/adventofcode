package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/21">Monkey Math</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class MonkeyMath implements LongSolver {

    @Override
    public long solve(Stream<String> lines) {
        Map<String, Value> values = parse(lines);
        evaluate(values);
        return solve(values);
    }

    /**
     * Parses the puzzle input.
     * 
     * @param lines puzzle input
     * @return a (mutable) map of all monkey values, indexed by the monkey's name
     */
    protected Map<String, Value> parse(Stream<String> lines) {
        Map<String, Value> values = new HashMap<>();
        lines.map(line -> line.split(": "))
                .peek(parts -> {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Unable to parse puzzle input.");
                    }
                })
                .forEach(parts -> values.put(parts[0], Value.parse(parts[1])));
        return values;
    }
    
    /**
     * Evaluates all expressions which can be evaluated in the given map.
     * 
     * @param values map containing all values
     */
    private void evaluate(Map<String, Value> values) {
        boolean done = false;
        while (!done) {
            done = true;
            // Evaluate all expressions for which both operands are known number values
            for (String monkey : values.keySet()) {
                Value value = values.get(monkey);
                if (value instanceof ExpressionValue expressionValue) {
                    if (values.get(expressionValue.lhs()) instanceof NumberValue lhs 
                            && values.get(expressionValue.rhs()) instanceof NumberValue rhs) {
                        var numberValue = expressionValue.operator().apply(lhs, rhs);
                        values.put(monkey, numberValue);
                        done = false;
                    }
                }
            }
        }
    }
    
    /**
     * Solver method.
     * 
     * @param values map containing all values, where all values which can be evaluated have been
     * @return solution to the puzzle
     */
    protected abstract long solve(Map<String, Value> values);
}
 