package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 18 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Monster Messages</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonsterMessagesPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of messages which completely match rule 0
     */
    @Override
    public long solve(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        int emptyLineIndex = lines.indexOf("");
        
        List<String> ruleLines = new ArrayList<>(lines.subList(0, emptyLineIndex));
        int index8 = lines.indexOf("8: 42");
        lines.set(index8, "8: 42 | 42 8");
        int index11 = lines.indexOf("11: 42 31");
        lines.set(index11, "11: 42 31 | 42 11 31");
        
        Map<Integer, Rule> rules = Rule.parseRules(ruleLines);
        Rule rule0 = rules.get(Integer.valueOf(0));
        
        List<String> messages = lines.subList(emptyLineIndex + 1, lines.size());
        
        return messages.stream()
                .filter(message -> rule0.matches(message, rules))
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MonsterMessagesPart2 instance = new MonsterMessagesPart2();

        String result = instance.solve("input-day19-2020.txt");

        LOGGER.info(result);
    }
}
 