package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Current monkey situation.
 *
 * @author Martijn van de Rijdt
 */
record State(boolean worryLevelsManageable, List<Monkey> monkeys) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /**
     * Parses puzzle input.
     * 
     * @param lines puzzle input
     * @param worryLevelsManageable whether worry levels are still manageable (that is, only in part 1 of the puzzle)
     * @return initial state
     */
    static State parse(Stream<String> lines, boolean worryLevelsManageable) {
        var monkeys = Monkey.parse(lines.toList());
        return new State(worryLevelsManageable, monkeys);
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
        
        LOGGER.debug("Monkey inspects an item with a worry level of {}.", Integer.valueOf(worryLevel));
        
        worryLevel = monkey.operation().applyAsInt(worryLevel);
        LOGGER.debug("Worry level is updated by the monkey's operation to {}.", Integer.valueOf(worryLevel));
        
        if (worryLevelsManageable) {
            worryLevel = worryLevel / 3;
            LOGGER.debug("Monkey gets bored with item. Worry level is divided by 3 to {}.", Integer.valueOf(worryLevel));
        } else {
            // TODO reduce worry level if possible, in such a way that it does not impact upcoming tests
        }
        
        int targetMonkeyId;
        if (worryLevel % monkey.divisor() == 0) {
            LOGGER.debug("Current worry level is divisible by {}.", Integer.valueOf(monkey.divisor()));
            targetMonkeyId = monkey.targetMonkeyIfTrue();
        } else {
            LOGGER.debug("Current worry level is not divisible by {}.", Integer.valueOf(monkey.divisor()));
            targetMonkeyId = monkey.targetMonkeyIfFalse();
        }
        
        LOGGER.debug("Item with worry level {} is thrown to monkey {}.", Integer.valueOf(worryLevel), Integer.valueOf(targetMonkeyId));
        
        List<Monkey> newMonkeys = new ArrayList<>(monkeys);
        newMonkeys.set(monkeyId, monkey.inspectAndRemoveFirstItem());
        newMonkeys.set(targetMonkeyId, monkeys.get(targetMonkeyId).addItem(new Item(worryLevel)));
        
        return new State(worryLevelsManageable, newMonkeys);
    }
    
    /**
     * Calculates the monkey business in this situation,
     * by mulitplying the number of items inspected by the two most active monkeys.
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