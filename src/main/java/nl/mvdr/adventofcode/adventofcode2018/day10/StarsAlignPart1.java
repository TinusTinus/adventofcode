package nl.mvdr.adventofcode.adventofcode2018.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to part 1 of the day 10 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/10">The Stars Align</a>.
 *
 * @author Martijn van de Rijdt
 */
public class StarsAlignPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StarsAlignPart1.class);
    
    private static final Pattern PATTERN = Pattern.compile("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>");
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        // Parse the input.
        Set<Star> stars = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(PATTERN::matcher)
                .peek(Matcher::matches)
                .map(matcher -> {
                    String pointX = matcher.group(1);
                    String pointY = matcher.group(2);
                    Point location = new Point(Integer.parseInt(pointX), Integer.parseInt(pointY));
                    String velocityX = matcher.group(3);
                    String velocityY = matcher.group(4);
                    Point velocity = new Point(Integer.parseInt(velocityX), Integer.parseInt(velocityY));
                    return new Star(location, velocity);
                })
                .collect(Collectors.toSet());

        log(stars, 0);
        for (int second = 1; second < 100_000; second++) {
            stars = stars.stream()
                     .map(Star::tick)
                     .collect(Collectors.toSet());
            log(stars, second);
        }
        
        return null;
    }

    /**
     * Logs an ASCII representation of the given stars.
     * 
     * @param stars stars
     */
    private void log(Set<Star> stars, int second) {
        // Construct a rectangle containing all of the stars
        int minX = stars.stream().map(Star::getLocation).mapToInt(Point::getX).min().getAsInt();
        int maxX = stars.stream().map(Star::getLocation).mapToInt(Point::getX).max().getAsInt();
        int minY = stars.stream().map(Star::getLocation).mapToInt(Point::getY).min().getAsInt();
        int maxY = stars.stream().map(Star::getLocation).mapToInt(Point::getY).max().getAsInt();

        int height = maxY - minY;
        
        if (height <= 10) {
            StringBuilder builder = new StringBuilder();
            for (int y = minY; y != maxY + 1; y++) {
                for (int x = minX; x != maxX + 1; x++) {
                    Point point = new Point(x, y);
                    char c;
                    if (stars.stream().anyMatch(star -> point.equals(star.getLocation()))) {
                        c = '#';
                    } else {
                        c = '.';
                    }
                    builder.append(c);
                }
                builder.append("\n");
            }
            LOGGER.info("After {} seconds: \n{}", Integer.valueOf(second), builder);
        }
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        StarsAlignPart1 instance = new StarsAlignPart1();

        instance.solve("input-day10-2018.txt");
    }
}
