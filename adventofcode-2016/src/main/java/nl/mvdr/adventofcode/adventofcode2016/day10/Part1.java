package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part1 extends BalanceBotsSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);
    
    private final Microchip firstMicrochip;
    private final Microchip secondMicrochip;
    
    private Part1(Microchip firstMicrochip, Microchip secondMicrochip) {
        this.firstMicrochip = firstMicrochip;
        this.secondMicrochip = secondMicrochip;
    }
    
    Part1(int firstMicrochipValue, int secondMicrochipValue) {
        this(new Microchip(firstMicrochipValue), new Microchip(secondMicrochipValue));
    }
    
    public Part1() {
        this(61, 17);
    }
    
    @Override
    protected int solve(State initialState, Map<Bot, Rule> rules) {
        var responsibleBot = initialState.findResponsibleBot(firstMicrochip, secondMicrochip, rules);
        return responsibleBot.number();
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day10-2016.txt");

        LOGGER.info(result);
    }
}
