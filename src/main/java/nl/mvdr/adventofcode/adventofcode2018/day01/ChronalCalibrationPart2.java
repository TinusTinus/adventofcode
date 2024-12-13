package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 1 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/1">Chronal Calibration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCalibrationPart2.class);
    
    @Override
    public int solve(Stream<String> lines) {
        List<Integer> frequencyChanges = lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
                // parse each line to an integer
                .mapToInt(Integer::parseInt)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        int index = 0;
        int frequency = 0;
        
        List<Integer> pastFrequencies = new ArrayList<>();
        
        while (!pastFrequencies.contains(Integer.valueOf(frequency))) {
            pastFrequencies.add(Integer.valueOf(frequency));
            
            int frequencyChange = frequencyChanges.get(index).intValue();
            frequency = frequency + frequencyChange;
            
            index = (index + 1) % frequencyChanges.size();
        }
        
        return frequency;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCalibrationPart2 instance = new ChronalCalibrationPart2();

        String result = instance.solve("input-day01-2018.txt");

        LOGGER.info(result);
    }
}
