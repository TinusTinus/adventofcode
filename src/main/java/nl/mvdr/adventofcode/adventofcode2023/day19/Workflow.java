package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A workflow.
 * 
 * @param name the name of this workflow
 * @param rules the rules in order;
 *      should typically contain a bunch of conditional rules followed by a single absolute rule
 * @author Martijn van de Rijdt
 */
record Workflow(String name, List<Rule> rules) {
    
    /**
     * Parses workflows.
     * 
     * @param lines list of strings, where each string contains the textual representation of a workflow
     * @return workflows, indexed by name
     */
    static Map<String, Workflow> parse(List<String> lines) {
        return lines.stream()
                .map(Workflow::parse)
                .collect(Collectors.toMap(Workflow::name, Function.identity()));
    }
    
    /**
     * Parses a workflow.
     * 
     * @param text textual representation of a workflow, for example: "ex{x>10:one,m<20:two,a>30:R,A}"
     * @return workflow
     */
    private static Workflow parse(String text) {
        var parts = text.split("\\{");
        if (parts.length != 2 || !parts[1].endsWith("}")) {
            throw new IllegalArgumentException("Invalid use of brackets: " + text);
        }
        var name = parts[0];
        
        String rulesString = parts[1].substring(0, parts[1].length() - 1);
        var rules = Rule.parse(rulesString);
        
        return new Workflow(name, rules);
    }
    
    /**
     * Checks whether this workflow accepts the given part.
     * 
     * @param part part
     * @param workflows all workflows, indexed by name
     * @return whether the part is accepted
     */
    boolean accepts(Part part, Map<String, Workflow> workflows) {
        var target = apply(part);
        return switch(target) {
            case "A" -> true;
            case "R" -> false;
            default -> workflows.get(target).accepts(part, workflows);
        };
    }
    
    /**
     * Applies this workflow to the given part.
     * 
     * @param part the part to check
     * @return "A" for accept, or "R" for reject, or otherwise the name of the next workflow to apply
     */
    private String apply(Part part) {
        return rules.stream()
                .filter(rule -> rule.apply(part))
                .map(Rule::target)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No applicable rule found for part " + part + " in workflow " + this));
    }
    
    /**
     * Determines which subranges of the given part range are accepted by this workflow.
     * 
     * @param partRange range of parts
     * @return part ranges which are accepted by this workflow
     */
    Set<PartRange> filter(PartRange partRange, Map<String, Workflow> workflows) {
        Set<PartRange> result = new HashSet<>();
        PartRange remainingPartRange = partRange;
        var ruleIndex = 0;
        while (!remainingPartRange.isEmpty()) {
            var rule = rules.get(ruleIndex);
            var filterResult = rule.filter(remainingPartRange);
            Set<PartRange> acceptingPartRanges = switch(rule.target()) {
                case "A" -> Set.of(filterResult.applies());
                case "R" -> Set.of();
                default -> workflows.get(rule.target()).filter(filterResult.applies(), workflows);
            };
            result.addAll(acceptingPartRanges);
            remainingPartRange = filterResult.doesNotApply();
            ruleIndex++;
        }
        return result;
    }
}
