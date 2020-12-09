package nl.mvdr.adventofcode.adventofcode2020.day07;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

/**
 * A luggage rule.
 *
 * @author Martijn van de Rijdt
 */
record Rule(String container, MultiSet<String> contents) {
    
    /**
     * Parses the specification of a list of rules.
     * 
     * @param lines puzzle input
     * @return rules
     */
    static List<Rule> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(Rule::parseRule)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses the specification for a single rule.
     * 
     * @param line textual representation of a rule, for example: "light red bags contain 1 bright white bag, 2 muted yellow bags."
     * @return rule
     */
    static Rule parseRule(String line) {
        String[] parts = line.split(" bags contain");
        
        String container = parts[0];
        
        MultiSet<String> contents = new HashMultiSet<>();
        Pattern pattern = Pattern.compile(" (\\d+) ([a-z]+ [a-z]+) bag[s]?[,\\.]");
        Matcher matcher = pattern.matcher(line);
        while(matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            String type = matcher.group(2);
            contents.add(type, number);
        }
        
        return new Rule(container, contents);
    }
}
