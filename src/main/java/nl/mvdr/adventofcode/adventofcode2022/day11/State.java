package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Current monkey situation.
 *
 * @param monkeys the monkeys, indexed by their id
 * @param divideByThree whether worry levels are divided by three on each inspection (that is, only in part 1 of the puzzle)
 * @param divisorLcm cached value of the least common multiple of the monkeys' divisors
 * @author Martijn van de Rijdt
 */
record State(List<Monkey> monkeys, boolean divideByThree, int divisorLcm) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /**
     * Creates the initial state based on the puzzle input.
     * 
     * @param lines puzzle input
     * @param divideByThree whether worry levels are divided by three on each inspection (that is, only in part 1 of the puzzle)
     * @return initial state
     */
    static State init(Stream<String> lines, boolean divideByThree) {
        var monkeys = Monkey.parse(lines.toList());
        var lcm = monkeys.stream()
                .mapToInt(Monkey::divisor)
                .distinct()
                .reduce(ArithmeticUtils::lcm)
                .orElseThrow();
        return new State(monkeys, divideByThree, lcm);
    }
    
    /**
     * Performs the given number of rounds.
     * 
     * @param rounds number of rounds to perform
     * @return updated state
     */
    State performRounds(int rounds) {
        var result = this;
        for (var round = 0; round != rounds; round++) {
            LOGGER.debug("After {} rounds:", Integer.valueOf(round));
            for (int monkeyId = 0; monkeyId != monkeys.size(); monkeyId++) {
                LOGGER.debug("Monkey {}: {}", Integer.valueOf(monkeyId), result.monkeys().get(monkeyId).items());
            }
            
            result = result.performRound();
        }
        return result;
    }
    
    /**
     * Performs a single round.
     * 
     * @return updated state
     */
    private State performRound() {
        var result = this;
        for (var monkeyId = 0; monkeyId != monkeys.size(); monkeyId++) {
            result = result.performTurn(monkeyId);
        }
        return result;
    }
    
    /**
     * Performs a single turn for the monkey with the given id.
     * 
     * @param monkeyId id of the monkey
     * @return updated state
     */
    private State performTurn(int monkeyId) {
        LOGGER.debug("Monkey {}:", Integer.valueOf(monkeyId));
        var result = this;
        while (!result.monkeys().get(monkeyId).items().isEmpty()) {
            result = result.handleItem(monkeyId);
        }
        return result;
    }
    
    /**
     * Handles a single item for the monkey with the given id.
     * 
     * @param monkeyId id of the monkey; this monkey must have at least one item
     * @return updated state
     */
    private State handleItem(int monkeyId) {
        var monkey = monkeys.get(monkeyId);
        var item = monkey.items().get(0);
        var worryLevel = item.worryLevel();
        
        LOGGER.debug("Monkey inspects an item with a worry level of {}.", worryLevel);
        
        worryLevel = monkey.operation().apply(worryLevel);
        LOGGER.debug("Worry level is updated by the monkey's operation to {}.", worryLevel);
        
        if (divideByThree) {
            worryLevel = worryLevel.divide(BigInteger.valueOf(3));
            LOGGER.debug("Monkey gets bored with item. Worry level is divided by 3 to {}.", worryLevel);
        } else {
            // Reduce worry level in such a way that it does not impact upcoming tests.
            // This prevents the performance issues which would occur if worry levels kept increasing.
            worryLevel = worryLevel.divideAndRemainder(BigInteger.valueOf(divisorLcm))[1];
            LOGGER.debug("Worry level is reduced to {} in order to keep it manageable.", worryLevel);
        }
        
        int targetMonkeyId;
        if (worryLevel.divideAndRemainder(BigInteger.valueOf(monkey.divisor()))[1].equals(BigInteger.ZERO)) {
            LOGGER.debug("Current worry level is divisible by {}.", Integer.valueOf(monkey.divisor()));
            targetMonkeyId = monkey.targetMonkeyIfTrue();
        } else {
            LOGGER.debug("Current worry level is not divisible by {}.", Integer.valueOf(monkey.divisor()));
            targetMonkeyId = monkey.targetMonkeyIfFalse();
        }
        
        LOGGER.debug("Item with worry level {} is thrown to monkey {}.", worryLevel, Integer.valueOf(targetMonkeyId));
        
        List<Monkey> newMonkeys = new ArrayList<>(monkeys);
        newMonkeys.set(monkeyId, monkey.inspectAndRemoveFirstItem());
        newMonkeys.set(targetMonkeyId, monkeys.get(targetMonkeyId).addItem(new Item(worryLevel)));
        
        return new State(newMonkeys, divideByThree, divisorLcm);
    }
    
    /**
     * Calculates the monkey business in this situation,
     * by multiplying the number of items inspected by the two most active monkeys.
     * 
     * @return monkey business in this situation
     */
    long calculateMonkeyBusiness() {
        return monkeys.stream()
                .mapToLong(Monkey::itemsInspected)
                .sorted()
                .skip(monkeys.size() - 2)
                .reduce((i, j) -> i * j)
                .orElseThrow();
    }
}
