package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 18 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Monster Messages</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class MonsterMessages implements LongSolver {

    private boolean replaceRules;
    
    /**
     * Constructor.
     * 
     * @param replaceRules whether rules 8 and 11 should be replaced, as specified in part 2 of the puzzle
     */
    MonsterMessages(boolean replaceRules) {
        super();
        this.replaceRules = replaceRules;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return number of messages which completely match rule 0
     */
    @Override
    public long solve(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        int emptyLineIndex = lines.indexOf("");
        
        List<String> ruleLines = lines.subList(0, emptyLineIndex);
        
        if (replaceRules) {
            ruleLines = new ArrayList<>(ruleLines);
            // Replace the rules as specified in part 2
            ruleLines.set(ruleLines.indexOf("8: 42"), "8: 42 | 42 8");
            ruleLines.set(ruleLines.indexOf("11: 42 31"), "11: 42 31 | 42 11 31");
        }
        
        Map<Integer, Rule> rules = Rule.parseRules(ruleLines);
        Rule rule0 = rules.get(Integer.valueOf(0));
        
        List<String> messages = lines.subList(emptyLineIndex + 1, lines.size());
        
        return messages.stream()
                .filter(message -> rule0.matches(message, rules))
                .count();
    }
}
 