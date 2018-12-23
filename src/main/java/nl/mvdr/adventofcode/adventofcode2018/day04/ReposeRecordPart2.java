package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 4 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/4">Repose Record</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart2 implements PathSolver {
    @Override
    public String solve(Path inputFilePath) throws IOException {
        return null; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReposeRecordPart2 instance = new ReposeRecordPart2();

        String result = instance.solve("input-day04-2018.txt");

        System.out.println(result);
    }
}
