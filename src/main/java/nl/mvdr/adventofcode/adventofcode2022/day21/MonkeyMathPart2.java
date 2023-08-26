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
        // Overwrite the special "humn" value.
        result.put("humn", SearchedValue.INSTANCE);
        return result;
    }
    
    @Override
    protected long solve(Map<String, Value> values) {
        ExpressionValue root = (ExpressionValue) values.get("root");
        
        // TODO
        
        return 0L;
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
 