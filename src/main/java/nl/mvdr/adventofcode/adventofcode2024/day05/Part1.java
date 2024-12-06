package nl.mvdr.adventofcode.adventofcode2024.day05;

import nl.mvdr.adventofcode.LongSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> linesStream) {
    	var lines = linesStream.toList();
    	var indexOfEmptyLine = lines.indexOf("");
    	
    	var rules = lines.subList(0, indexOfEmptyLine)
    			.stream()
    			.map(Rule::parse)
    			.toList();
    	var patches = lines.subList(indexOfEmptyLine + 1, lines.size())
    			.stream()
    			.map(Patch::parse)
    			.toList();
    	
        return 0; // TODO
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day05-2024.txt");

        LOGGER.info(result);
    }
}
 