package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;

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
     * Parses a list of workflows.
     * 
     * @param lines list of strings, where each string contains the textual representation of a workflow
     * @return workflows
     */
    static List<Workflow> parse(List<String> lines) {
        return lines.stream()
                .map(Workflow::parse)
                .toList();
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
     * Applies this workflow to the given part.
     * 
     * @param part the part to check
     * @return "A" for accept, or "R" for reject, or otherwise the name of the next workflow to apply
     */
    String apply(Part part) {
        return rules.stream()
                .filter(rule -> rule.apply(part))
                .map(Rule::target)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No applicable rule found for part " + part + " in workflow " + this));
    }
}
