package nl.mvdr.adventofcode.adventofcode2018.day10;

import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 10 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/10">The Stars Align</a>.
 *
 * @author Martijn van de Rijdt
 */
public class StarsAlign implements LinesSolver<Void> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StarsAlign.class);
    
    private static final Pattern PATTERN = Pattern.compile("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>");
    
    @Override
    public Void solve(Stream<String> lines) {
        // Parse the input.
        Set<Star> stars = lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
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
        
        // The result can be seen in the log output.
        
        return null;
    }

    /**
     * Logs an ASCII representation of the given stars.
     * 
     * @param stars stars
     */
    private void log(Set<Star> stars, int second) {
        // Construct a rectangle containing all of the stars
        int minX = stars.stream().map(Star::getLocation).mapToInt(Point::x).min().getAsInt();
        int maxX = stars.stream().map(Star::getLocation).mapToInt(Point::x).max().getAsInt();
        int minY = stars.stream().map(Star::getLocation).mapToInt(Point::y).min().getAsInt();
        int maxY = stars.stream().map(Star::getLocation).mapToInt(Point::y).max().getAsInt();

        int height = maxY - minY;
        
        if (height <= 8) {
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
        StarsAlign instance = new StarsAlign();

        instance.solve("input-day10-2018.txt");
    }
}
