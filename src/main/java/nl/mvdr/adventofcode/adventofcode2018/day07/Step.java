package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of a step in the process of assembling a sleigh.
 *
 * @author Martijn van de Rijdt
 */
class Step {
    private static final Pattern PATTERN = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.");
    
    /** Identification of this step. Should be a capital letter. */
    private final char id;
    
    /** Steps that need to be completed before work on this step can be started. */
    private final Set<Step> prerequisites;
    
    /** Time remaining before this step is complete, in seconds. */
    int remainingTime;
    
    /**
     * Constructor.
     * 
     * @param id identification of this step; should be a capital letter
     * @param baseTime basic number of seconds needed to complete any step (0 in the example, 60 in the actual puzzle input)
     */
    private Step(char id, int baseTime) {
        super();
        this.id = id;
        this.prerequisites = new HashSet<>();
        this.remainingTime = baseTime + id - 'A' + 1;
    }
    
    /**
     * Adds a prerequisite to this step.
     * 
     * @param prerequisite prerequisite step
     */
    private void addPrerequisite(Step prerequisite) {
        this.prerequisites.add(prerequisite);
    }

    /** @return identification of this step; should be a capital letter */
    char getId() {
        return id;
    }
    
    /** @return identifications of the steps that need to be completed before work on this step can be started */
    Set<Step> getPrerequisites() {
        return prerequisites;
    }

    /** @return time remaining before this step is complete, in seconds */
    int getRemainingTime() {
        return remainingTime;
    }
    
    /** @return whether this step has been completed */
    boolean isDone() {
        return remainingTime <= 0;
    }
    
    /** Works on this step for a second. This decreases the remaining time of this step. */
    void work() {
        remainingTime--;
    }
    
    @Override
    public String toString() {
        return "" + id;
    }
    
    /**
     * Builds a list of steps based on the text in the given text file.
     * 
     * @param inputFilePath file path of the text file
     * @param baseTime basic number of seconds needed to complete any step (0 in the example, 60 in the actual puzzle input)
     * @return list of steps, sorted by ascending identification
     * @throws IOException exception when reading the text  file
     */
    static List<Step> parse(Path inputFilePath, int baseTime) throws IOException {
        List<Step> result = new ArrayList<Step>();
        
        Files.lines(inputFilePath)
            // ignore empty lines (the last line in the file)
            .filter(Objects::nonNull)
            .filter(line -> !line.isBlank())
            .map(line -> PATTERN.matcher(line))
            .peek(Matcher::matches)
            .forEach(matcher -> {
                char prerequisiteStepId = matcher.group(1).charAt(0);
                char stepId = matcher.group(2).charAt(0);
                
                Step prerequisite = result.stream()
                        .filter(step -> step.id == prerequisiteStepId)
                        .findFirst()
                        .orElseGet(() -> {
                            Step newStep = new Step(prerequisiteStepId, baseTime);
                            result.add(newStep);
                            return newStep;
                        });
                        
                if (!result.stream().anyMatch(step -> step.id == prerequisiteStepId)) {
                    result.add(new Step(prerequisiteStepId, baseTime));
                }
                
                result.stream()
                    .filter(step -> step.id == stepId)
                    .findFirst()
                    .orElseGet(() -> {
                        Step step = new Step(stepId, baseTime);
                        result.add(step);
                        return step;
                    })
                    .addPrerequisite(prerequisite);
            });
        
        result.sort(Comparator.comparing(Step::getId));
        
        return result;
    }
}
