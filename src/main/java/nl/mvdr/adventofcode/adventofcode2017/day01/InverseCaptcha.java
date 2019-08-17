package nl.mvdr.adventofcode.adventofcode2017.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 1 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/1">Inverse Captcha</a>.
 *
 * @author Martijn van de Rijdt
 */
public class InverseCaptcha implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InverseCaptcha.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Integer> digits = Files.lines(inputFilePath)
                .findFirst()
                .get()
                .chars()
                .mapToObj(c -> "" + (char)c)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        int result = IntStream.range(0, digits.size())
                .filter(i -> digits.get(i).equals(digits.get((i + 1) % digits.size())))
                .map(digits::get)
                .sum();
        
        return Integer.valueOf(result);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        InverseCaptcha instance = new InverseCaptcha();

        String result = instance.solve("input-day01-2017.txt");

        LOGGER.info(result);
    }
}
