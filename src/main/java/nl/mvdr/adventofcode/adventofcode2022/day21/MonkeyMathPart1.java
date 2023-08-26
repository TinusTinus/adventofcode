package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/21">Monkey Math</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart1 extends MonkeyMath {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMathPart1.class);

    @Override
    protected long solve(Map<String, Value> values) {
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
 