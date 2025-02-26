package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends BalanceBotsSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);
    
    @Override
    protected int solve(State initialState, Map<Bot, Rule> rules) {
        var endState = initialState.applyUntilDone(rules);
        
        return IntStream.of(0, 1, 2)
                .mapToObj(Output::new)
                .map(endState.microchips()::get)
                .map(microchips -> microchips.iterator().next())
                .mapToInt(Microchip::value)
                .reduce((i, j) -> i * j)
                .orElseThrow(); 
    }
    
    public static void main(String[] args) {
        Part2 instance = new Part2();

        String result = instance.solve("input-day10-2016.txt");

        LOGGER.info(result);
    }
}
