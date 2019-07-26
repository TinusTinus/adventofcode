package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A tiny robot.
 * 
 * @author Martijn van de Rijdt
 */
class Nanobot {
    private final Point position;
    private final int radius;

    /**
     * Parses a text file containing a set of nanobots.
     * 
     * @param inputFilePath path to the input text file
     * @return nanobots
     * @throws IOException in case the input text file could not be read
     */
    static Set<Nanobot> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(Nanobot::parse)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a line of text representing a nanobot.
     * 
     * For example: "pos=<0,0,0>, r=4".
     * 
     * @param line line of text from the puzzle input
     * @return nanobot
     */
    private static Nanobot parse(String line) {
        Pattern pattern = Pattern.compile("pos=(<-?\\d+,-?\\d+,-?\\d+>), r=(\\d+)");

        Matcher matcher = pattern.matcher(line);
        matcher.matches();

        Nanobot result;

        if (matcher.matches()) {
            Point position = Point.parse(matcher.group(1));
            int radius = Integer.parseInt(matcher.group(2));
            result = new Nanobot(position, radius);
        } else {
            throw new IllegalArgumentException("Unable to parse line: " + line);
        }

        return result;
    }
    
    /**
     * Constructor.
     * 
     * @param position location of the nanobot
     * @param radius radius of the nanobot
     */
    private Nanobot(Point position, int radius) {
        super();
        this.position = position;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "pos=" + position + ", r=" + radius;
    }
}
