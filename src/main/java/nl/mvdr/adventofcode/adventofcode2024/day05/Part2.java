package nl.mvdr.adventofcode.adventofcode2024.day05;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> linesStream) {
    	var lines = linesStream.toList();
    	var indexOfEmptyLine = lines.indexOf("");
    	if (indexOfEmptyLine < 0) {
    	    throw new IllegalArgumentException("Empty line not found in input: " + lines);
    	}
    	
    	var rules = lines.subList(0, indexOfEmptyLine)
    			.stream()
    			.map(Rule::parse)
    			.collect(Collectors.toSet());
    	
        return lines.subList(indexOfEmptyLine + 1, lines.size())
                .stream()
                .map(Update::parse)
                .filter(update -> !update.satisfies(rules))
                .map(update -> update.sort(rules))
                .mapToInt(Update::middlePage)
                .sum();
    }

    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day05-2024.txt");

        LOGGER.info(result);
    }
}
 