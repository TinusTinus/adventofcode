package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/21">Monkey Math</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMathPart1.class);

    @Override
    public int solve(Stream<String> lines) {
        // Construct a map of all monkey values, indexed by the monkey's name.
        Map<String, Value> values = new HashMap<>();
        lines.map(line -> line.split(": "))
                .peek(parts -> {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Unable to parse puzzle input.");
                    }
                })
                .forEach(parts -> values.put(parts[0], Value.parse(parts[1])));
        
        while (values.get("root") instanceof ExpressionValue) {
            // Evaluate all expressions for which both operands are known number values
            for (String monkey : values.keySet()) {
                Value value = values.get(monkey);
                if (value instanceof ExpressionValue expressionValue) {
                    if (values.get(expressionValue.lhs()) instanceof NumberValue lhs 
                            && values.get(expressionValue.rhs()) instanceof NumberValue rhs) {
                        var numberValue = expressionValue.operator().apply(lhs, rhs);
                        values.put(monkey, numberValue);
                    }
                }
            }
        }
        
        NumberValue numberValue = (NumberValue) values.get("root");
        return numberValue.number();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyMathPart1();

        var result = instance.solve("input-day21-2022.txt");

        LOGGER.info(result);
    }
}
 