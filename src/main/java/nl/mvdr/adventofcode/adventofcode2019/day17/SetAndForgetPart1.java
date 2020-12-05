package nl.mvdr.adventofcode.adventofcode2019.day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 17 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/17">Set and Forget</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetAndForgetPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the sum of the alignment parameters
     */
    @Override
    public int solve(Stream<String> lines) {
        String scaffoldString = getScaffoldString(lines);
        
        Set<Point> scaffolds = parseScaffolds(scaffoldString);
        
        return scaffolds.stream()
                .filter(s -> scaffolds.contains(s.aboveNeighbour()))
                .filter(s -> scaffolds.contains(s.belowNeighbour()))
                .filter(s -> scaffolds.contains(s.leftNeighbour()))
                .filter(s -> scaffolds.contains(s.rightNeighbour()))
                .mapToInt(intersection -> intersection.x() * intersection.y())
                .sum();
    }

    /**
     * Gets the string representation of the scaffolding, based on the puzzle input.
     * 
     * @param lines puzzle input, containing an Intcode program to display scaffolding
     * @return string representation of the scaffolding
     */
    private String getScaffoldString(Stream<String> lines) {
        List<Long> programOutput = new ArrayList<>();
        Program program = Program.parse(lines.findFirst().orElseThrow(),
                () -> { throw new NoSuchElementException("No input available"); }, 
                programOutput::add);
        program.execute();
        
        LOGGER.debug("Program output: {}", programOutput);
        
        String scaffoldString = programOutput.stream()
                .mapToInt(Math::toIntExact)
                .mapToObj(i -> "" + (char) i)
                .collect(Collectors.joining());
        
        LOGGER.debug("Scaffolding string:\n{}", scaffoldString);
        
        return scaffoldString;
    }
    
    /**
     * Parses the given string.
     * 
     * @param scaffoldString string representation of scaffolding
     * @return coordinates of scaffolds
     */
    private Set<Point> parseScaffolds(String scaffoldString) {
        Set<Point> scaffolds = new HashSet<>();
        String[] parts = scaffoldString.split("\n");
        for (int y = 0; y != parts.length; y++) {
            for (int x = 0; x != parts[y].length(); x++) {
                if (parts[y].charAt(x) == '#') {
                    scaffolds.add(new Point(x, y));
                }
            }
        }
        return scaffolds;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SetAndForgetPart1 instance = new SetAndForgetPart1();

        String result = instance.solve("input-day17-2019.txt");

        LOGGER.info(result);
    }
}
 