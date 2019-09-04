package nl.mvdr.adventofcode.adventofcode2017.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2017.day10.KnotHashPart2;
import nl.mvdr.adventofcode.adventofcode2017.knothash.KnotHasher;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/14">Disk Defragmentation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart1 implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskDefragmentationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return squares
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        String input = Files.lines(inputFilePath).findFirst().get();
        LOGGER.debug("Puzzle input: {}", input);
        
        KnotHasher hasher = new KnotHashPart2();
        
        long result = IntStream.range(0, 128)
                .mapToObj(i -> input + "-" + i)
                .map(hasher::knotHash)
                .map(this::toBinary)
                .mapToLong(this::countOnes)
                .sum();
                
        return Long.valueOf(result);
    }
    
    /**
     * Converts the given hexadecimal string to binary.
     *  
     * @param hexString string containing characters representing hexadecimal numbers,
     *          for example: "a0c2017"
     * @return string where every hexadecimal number has been replaced by a 4-digit binary number,
     *          for example: "10100000110000100000000101110000"
     */
    private String toBinary(String hexString) {
        return hexString.chars()
            .map(c -> Integer.parseInt("" + (char)c, 16))
            .mapToObj(Integer::toBinaryString)
            .map(binaryString -> StringUtils.leftPad(binaryString, 4, '0'))
            .collect(Collectors.joining());
    }
    
    /**
     * Counts the number of occurrences of '1' in the given string.
     * 
     * @param string string
     * @return number of ones
     */
    private long countOnes(String string) {
        return string.chars()
                .filter(i -> ((char) i) == '1')
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DiskDefragmentationPart1 instance = new DiskDefragmentationPart1();

        String result = instance.solve("input-day14-2017.txt");

        LOGGER.info(result);
    }
}
