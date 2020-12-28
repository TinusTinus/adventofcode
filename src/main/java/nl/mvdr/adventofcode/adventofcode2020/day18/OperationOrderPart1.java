package nl.mvdr.adventofcode.adventofcode2020.day18;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 18 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Operation Order</a>.
 *
 * @author Martijn van de Rijdt
 */
public class OperationOrderPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationOrderPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return sum of the values of the expressions on each line of the input
     */
    @Override
    public long solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(line -> Expression.parse(line, false))
                .peek(expression -> LOGGER.debug("Expression: {}", expression))
                .mapToLong(Expression::evaluate)
                .sum();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        OperationOrderPart1 instance = new OperationOrderPart1();

        String result = instance.solve("input-day18-2020.txt");

        LOGGER.info(result);
    }
}
 