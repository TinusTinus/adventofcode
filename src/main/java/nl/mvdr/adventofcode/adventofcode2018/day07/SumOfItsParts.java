package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 7 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/7">The Sum of Its Parts</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class SumOfItsParts implements PathSolver {
    
    private final SumOfItsPartsSolution solution;
    private final int baseTime;
    private final int workers;
    
    /** Constructor. */
    protected SumOfItsParts(SumOfItsPartsSolution solution, int baseTime, int workers) {
        super();
        this.solution = solution;
        this.baseTime = baseTime;
        this.workers = workers;
    }
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        
        List<Step> steps = Step.parse(inputFilePath, baseTime);
        
        String stepsPerformed = "";
        int timeSpent = 0;
        
        Optional<Step> step = nextStep(steps);
        while (step.isPresent()) {
            while (!step.get().isDone()) {
                step.get().work();
                timeSpent++;
            }
            stepsPerformed = stepsPerformed + step.get().getId();
            step = nextStep(steps);
        }
        
        String result;
        if (solution == SumOfItsPartsSolution.STEPS) {
            result = stepsPerformed; 
        } else if (solution == SumOfItsPartsSolution.TIME) {
            result = "" + timeSpent;
        } else {
            throw new IllegalStateException("Unexpected solution specified: " + solution);
        }
        return result;
    }
    
    /**
     * Determines the next step to be performed.
     * 
     * If no more steps can be performed, this method returns an empty optional.
     * This typically means that all steps are done.
     * It could also mean that a deadlock has occurred, because prerequisites cannot be filled.
     * 
     * @param steps all steps; should be sorted in alphabetical order
     * @return next step to be performed; empty in case no more steps can be performed
     */
    private Optional<Step> nextStep(List<Step> steps) {
        return steps.stream()
                .filter(step -> !step.isDone())
                .filter(step -> step.getPrerequisites().stream().allMatch(Step::isDone))
                .findFirst();
    }
}
