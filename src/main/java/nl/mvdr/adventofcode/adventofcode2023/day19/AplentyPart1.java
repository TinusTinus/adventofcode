package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/19">Aplenty</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AplentyPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AplentyPart1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        List<String> lines = linesStream.toList();
        var emptyLineIndex = lines.indexOf("");
        
        var workflows = Workflow.parse(lines.subList(0, emptyLineIndex))
                .stream()
                .collect(Collectors.toMap(Workflow::name, Function.identity()));
        
        var parts = Part.parse(lines.subList(emptyLineIndex + 1, lines.size()));
        
        return parts.stream()
                .filter(part -> accept(part, workflows))
                .mapToInt(Part::totalRatings)
                .sum();
    }
    
    /**
     * Checks whether the given part is accepted by the given collection of workflows.
     * 
     * @param part part
     * @param workflows workflows, indexed by name
     * @return whether the workflows accept or reject the part
     */
    private static boolean accept(Part part, Map<String, Workflow> workflows) {
        var currentWorkflowName = "in";
        while (!Set.of("A", "R").contains(currentWorkflowName)) {
            var currentWorkflow = workflows.get(currentWorkflowName);
            Objects.requireNonNull(currentWorkflow, "No workflow found with name " + currentWorkflowName);
            currentWorkflowName = currentWorkflow.apply(part);
        }
        return "A".equals(currentWorkflowName);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new AplentyPart1();

        var result = instance.solve("input-day19-2023.txt");

        LOGGER.info(result);
    }
}
 