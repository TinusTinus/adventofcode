package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

/**
 * Representation of a single monkey.
 *
 * @param items the items currently being held by this monkey
 * @param operation the operation to perform on items' worry level
 * @param test the test to perform on items' worry level
 * @param targetMonkeyIfTrue where to throw an item if the test returns {@code true}
 * @param targetMonkeyIfFalse where to throw an item if the test returns {@code false}
 * @author Martijn van de Rijdt
 */
record Monkey(List<Item> items,
        IntUnaryOperator operation,
        IntPredicate test,
        int targetMonkeyIfTrue,
        int targetMonkeyIfFalse) {

    private static final String MONKEY_ID_LINE_PREFIX = "Monkey ";
    private static final String STARTING_ITEMS_LINE_PREFIX = "  Starting items: ";
    private static final String OPERATION_LINE_PREFIX = "  Operation: new = ";
    private static final String TEST_LINE_PREFIX = "  Test: divisible by ";
    private static final String TARGET_LINE_PREFIX = "    If %s: throw to monkey ";

    /**
     * Parses puzzle input into an initial list of monkeys.
     * 
     * Note that the index in the returned list corresponds to the monkey id.
     * 
     * @param input puzzle input represented as a list of lines
     * @return monkeys
     */
    static List<Monkey> parse(List<String> lines) {
        List<Monkey> result = new ArrayList<>();
        
        Iterator<String> linesIterator = lines.iterator();
        while (linesIterator.hasNext()) {
            var monkeyId = parseMonkeyId(linesIterator.next());
            if (monkeyId != result.size()) {
                throw new IllegalArgumentException("Unexpected monkey id: " + monkeyId);
            }
            
            var startingItems = parseStartingItems(linesIterator.next());
            var operation = parseOperation(linesIterator.next());
            var test = parseTest(linesIterator.next());
            var targetMonkeyIfTrue = parseTarget(linesIterator.next(), true);
            var targetMonkeyIfFalse = parseTarget(linesIterator.next(), false);
            result.add(new Monkey(startingItems, operation, test, targetMonkeyIfTrue, targetMonkeyIfFalse));
            
            // Skip empty line
            if (linesIterator.hasNext()) {
                var line = linesIterator.next();
                if (!line.isEmpty()) {
                    throw new IllegalArgumentException("Unexpected input: " + line);
                }
            }
        }
        
        return result;
    }
    /**
     * Parses a line from the puzzle input, representing a monkey's id number.
     * 
     * @param line line from the puzzle input, such as: "Monkey 1:"
     */
    private static int parseMonkeyId(String line) {
        if (!line.startsWith(MONKEY_ID_LINE_PREFIX) || !line.endsWith(":")) {
            throw new IllegalArgumentException("Expected start of a new monkey, unexpected input: " + line);
        }
        var monkeyIdString = line.substring(MONKEY_ID_LINE_PREFIX.length(), line.length() - 1);
        return Integer.parseInt(monkeyIdString);
    }
    /**
     * Parses a line from the puzzle input, representing the monkey's starting items.
     * 
     * @param line line from the puzzle input, such as: "  Starting items: 54, 65, 75, 74"
     * @return starting items
     */
    private static List<Item> parseStartingItems(String line) {
        if (!line.startsWith(STARTING_ITEMS_LINE_PREFIX)) {
            throw new IllegalArgumentException("Expected starting items line, unexpected input: " + line);
        }
        var startingItemsParts = line.substring(STARTING_ITEMS_LINE_PREFIX.length()).split(", ");
        return Stream.of(startingItemsParts)
                .mapToInt(Integer::parseInt)
                .mapToObj(Item::new)
                .toList();
    }
    /**
     * Parses a line from the puzzle input, representing the worry level operation.
     * 
     * @param line line from the puzzle input, such as: "  Operation: new = old + 6"
     * @return worry level operation
     */
    private static IntUnaryOperator parseOperation(String line) {
        if (!line.startsWith(OPERATION_LINE_PREFIX)) {
            throw new IllegalArgumentException("Expected operation line, unexpected input: " + line);
        }
        var operationString = line.substring(OPERATION_LINE_PREFIX.length());
        // We could write a complex expression parser here...
        // but there are only a few types of expressions in the puzzle example / input:
        //   old * old
        //   old * <n>
        //   old + <n>
        IntUnaryOperator operation;
        if ("old * old".equals(operationString)) {
            operation = old -> old * old;
        } else {
            var rhs = Integer.parseInt(operationString.substring(6));
            if (operationString.startsWith("old * ")) {
                operation = old -> old * rhs;
            } else if (operationString.startsWith("old + ")) {
                operation = old -> old + rhs;
            } else {
                throw new IllegalArgumentException("Unexpected operation: " + line);
            }
        }
        return operation;
    }
    
    /**
     * Parses a line from the puzzle input, representing the worry level test.
     * 
     * @param line line from the puzzle input, such as: "  Test: divisible by 19" 
     * @return worry level test
     */
    private static IntPredicate parseTest(String line) {
        if (!line.startsWith(TEST_LINE_PREFIX)) {
            throw new IllegalArgumentException("Expected test line, unexpected input: " + line);
        }
        var divisorString = line.substring(TEST_LINE_PREFIX.length());
        var divisor = Integer.parseInt(divisorString);
        return worryLevel -> worryLevel / divisor == 0;
    }
    
    /**
     * Parses a line from the puzzle input, representing a target.
     * 
     * @param line line from the puzzle input, such as: "    If true: throw to monkey "
     * @param guard the expected guard for this target line 
     * @return target monkey id
     */
    private static int parseTarget(String line, boolean guard) {
        var prefix = String.format(TARGET_LINE_PREFIX, Boolean.valueOf(guard));
        if (!line.startsWith(prefix)) {
            throw new IllegalArgumentException("Expected target line, unexpected input: " + line);
        }
        var targetString = line.substring(prefix.length());
        var target = Integer.parseInt(targetString);
        return target;
    }
}
