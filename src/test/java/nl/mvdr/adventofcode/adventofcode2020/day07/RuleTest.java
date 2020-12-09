package nl.mvdr.adventofcode.adventofcode2020.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Rule}.
 *
 * @author Martijn van de Rijdt
 */
public class RuleTest {
    /** Test case for {@link Rule#parseRule(String)}. */
    @Test
    public void testParse() {
        String ruleText = "bright white bags contain 1 shiny gold bag.";
        
        Rule rule = Rule.parseRule(ruleText);
        
        Assertions.assertEquals("bright white", rule.container());
        Assertions.assertEquals(1, rule.contents().size());
        Assertions.assertEquals(1, rule.contents().getCount("shiny gold"));
    }
    
    /** Test case for {@link Rule#parseRule(String)}. */
    @Test
    public void testParseMultipleContents() {
        String ruleText = "light red bags contain 1 bright white bag, 2 muted yellow bags.";
        
        Rule rule = Rule.parseRule(ruleText);
        
        Assertions.assertEquals("light red", rule.container());
        Assertions.assertEquals(3, rule.contents().size());
        Assertions.assertEquals(1, rule.contents().getCount("bright white"));
        Assertions.assertEquals(2, rule.contents().getCount("muted yellow"));
    }
}
