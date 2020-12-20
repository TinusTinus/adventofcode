package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of an enhancement rule from the puzzle input.
 *
 * @author Martijn van de Rijdt
 */
class EnhancementRule {
    
    private final Image input;
    private final Image output;
    
    /**
     * Parses the given text file into a set of rules.
     * 
     * @param lines puzzle input
     * @return rules
     */
    static Set<EnhancementRule> parse(Stream<String> lines) {
        return lines.filter(line -> !"".equals(line))
                .map(EnhancementRule::parseRule)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a single rule from a line from the puzzle input.
     * 
     * @param text text representation of a rule, for example: ".#./..#/### => #..#/..../..../#..#"
     * @return rule
     */
    private static EnhancementRule parseRule(String text) {
        String[] parts = text.split(" => ");
        
        Image input = Image.parse(parts[0]);
        Image output = Image.parse(parts[1]);
        
        return new EnhancementRule(input, output);
    }
    
    /**
     * Constructor.
     * 
     * @param input input pattern
     * @param output output pattern, which should replace the square that matches the input pattern
     */
    private EnhancementRule(Image input, Image output) {
        super();
        this.input = input;
        this.output = output;
    }
    
    Image getInput() {
        return input;
    }
    
    Image getOutput() {
        return output;
    }
}
