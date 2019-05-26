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
public class SumOfItsParts implements PathSolver {
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        
        List<Step> steps = Step.parse(inputFilePath, 0);
        
        String result = "";
        Optional<Step> step = nextStep(steps);
        while (step.isPresent()) {
            while (!step.get().isDone()) {
                step.get().work();
            }
            result = result + step.get().getId();
            step = nextStep(steps);
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

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SumOfItsParts instance = new SumOfItsParts();

        String result = instance.solve("input-day07-2018.txt");

        System.out.println(result);
    }
}
