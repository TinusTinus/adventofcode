package nl.mvdr.adventofcode.adventofcode2020.day07;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

/**
 * A luggage rule.
 *
 * @author Martijn van de Rijdt
 */
record Rule(String container, MultiSet<String> contents) {
    /**
     * Parses the specification for a single rule.
     * 
     * @param line textual representation of a rule, for example: "light red bags contain 1 bright white bag, 2 muted yellow bags."
     * @return rule
     */
    static Rule parseRule(String line) {
        Pattern pattern = Pattern.compile("([a-z]+ [a-z]+) bags contain(?: (\\d+) ([a-z]+ [a-z]+) bag[s]?[,\\.])+");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Rule could not be parsed: " + line);
        }
        String container = matcher.group(1);
        int number = Integer.parseInt(matcher.group(2));
        String contentType = matcher.group(3);
        
        MultiSet<String> contents = new HashMultiSet<>();
        contents.add(contentType, number);
        
        return new Rule(container, contents);
    }
}
