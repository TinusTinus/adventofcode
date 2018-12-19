package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 1 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/1">Chronal Calibration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart2 implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<Integer> frequencyChanges = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line to an integer
                .mapToInt(Integer::parseInt)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        int index = 0;
        int frequency = 0;
        
        List<Integer> pastFrequencies = new ArrayList<>();
        
        while (!pastFrequencies.contains(Integer.valueOf(frequency))) {
            pastFrequencies.add(Integer.valueOf(frequency));
            
            int frequencyChange = frequencyChanges.get(index);
            frequency = frequency + frequencyChange;
            
            index = (index + 1) % frequencyChanges.size();
        }
        
        return "" + frequency;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalCalibrationPart2 instance = new ChronalCalibrationPart2();

        String result = instance.solve("input-day01-2018.txt");

        System.out.println(result);
    }
}
