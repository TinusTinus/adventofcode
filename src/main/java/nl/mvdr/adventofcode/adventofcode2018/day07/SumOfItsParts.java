package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 7 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/7">The Sum of Its Parts</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class SumOfItsParts implements LinesSolver<String> {
    
    private final SumOfItsPartsSolution solution;
    private final int baseTime;
    private final int workers;
    
    /**
     * Constructor.
     * 
     * @param solution preferred output
     * @param baseTime basic number of seconds needed to complete any step (0 in the example, 60 in the actual puzzle input)
     * @param workers number of parallel workers
     */
    protected SumOfItsParts(SumOfItsPartsSolution solution, int baseTime, int workers) {
        super();
        this.solution = solution;
        this.baseTime = baseTime;
        this.workers = workers;
    }
    
    @Override
    public String solve(Stream<String> lines) {
        
        List<Step> steps = Step.parse(lines, baseTime);

        StringBuilder stepsPerformed = new StringBuilder();
        int timeSpent = 0;
        
        Set<Step> stepsInProgress = new HashSet<>();
        
        while(!steps.stream().allMatch(Step::isDone)) {
            // Start of a new second.
            // Find steps to start for any idle workers
            steps.stream()
                // Don't work on steps that have already been completed
                .filter(step -> !step.isDone())
                // Don't work on steps that someone is already working on
                .filter(step -> !stepsInProgress.contains(step))
                // Only work on steps for which all prerequisites have been met
                .filter(step -> step.getPrerequisites().stream().allMatch(Step::isDone))
                // Limit to the number of workers
                .limit(workers - stepsInProgress.size())
                .peek(stepsPerformed::append)
                .forEach(stepsInProgress::add);
            
            stepsInProgress.forEach(Step::work);
            timeSpent++;
            
            stepsInProgress.removeIf(Step::isDone);
        }
        
        String result;
        if (solution == SumOfItsPartsSolution.STEPS) {
            result = stepsPerformed.toString(); 
        } else if (solution == SumOfItsPartsSolution.TIME) {
            result = "" + timeSpent;
        } else {
            throw new IllegalStateException("Unexpected solution specified: " + solution);
        }
        return result;
    }
}
