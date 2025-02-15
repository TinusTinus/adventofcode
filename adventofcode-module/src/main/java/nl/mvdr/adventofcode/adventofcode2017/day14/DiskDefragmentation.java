package nl.mvdr.adventofcode.adventofcode2017.day14;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;
import nl.mvdr.adventofcode.adventofcode2017.day10.KnotHashPart2;
import nl.mvdr.adventofcode.adventofcode2017.knothash.KnotHasher;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/14">Disk Defragmentation</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class DiskDefragmentation implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskDefragmentation.class);
    
    @Override
    public int solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        LOGGER.debug("Puzzle input: {}", input);
        
        KnotHasher hasher = new KnotHashPart2();
        
        List<String> binaryStrings = IntStream.range(0, 128)
                .parallel()
                .mapToObj(i -> input + "-" + i)
                .map(hasher::knotHash)
                .map(this::toBinary)
                .toList();
        
        Set<Point> squares = IntStream.range(0, binaryStrings.size())
                .parallel()
                .boxed()
                .flatMap(i -> {
                    String binaryString = binaryStrings.get(i.intValue());
                    return IntStream.range(0, binaryString.length())
                        .filter(j -> binaryString.charAt(j) == '1')
                        .mapToObj(j -> new Point(i.intValue(), j));
                })
                .collect(Collectors.toSet());
        
        return solve(squares);
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
     * Solver method.
     * 
     * @param squares squares in the input
     * @return solution to the puzzle for the given input
     */
    protected abstract int solve(Set<Point> squares);
}
