package nl.mvdr.adventofcode.adventofcode2017.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a single line from the puzzle input.
 *
 * @author Martijn van de Rijdt
 */
class Line {
    /** Program id on the left-hand side of the line. */
    private final int leftHandSide;
    
    /** Program ids on the right-hand side of the line. */
    private final List<Integer> rightHandSide;

    /**
     * Parses the puzzle input.
     * 
     * @param inputFilePath path to the input text file
     * @return lines
     * @throws IOException in case the file could not be read
     */
    static List<Line> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(Line::parseLine)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single line from the puzzle input.
     * 
     * @param text text line to be parsed
     * @return line
     */
    private static Line parseLine(String text) {
        String[] parts = text.split(" <-> ");
        
        int leftHandSide = Integer.parseInt(parts[0]);
        
        List<Integer> rightHandSide = Stream.of(parts[1].split(", "))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
        
        return new Line(leftHandSide, rightHandSide);
    }
    
    /**
     * Constructor.
     * 
     * @param leftHandSide program id
     * @param rightHandSide list of program ids
     */
    private Line(int leftHandSide, List<Integer> rightHandSide) {
        super();
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }
    
    @Override
    public String toString() {
        return leftHandSide + " <-> " + rightHandSide.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
    }
    
    /** @return all program ids on this line (which are all connected to each other) */
    Set<Integer> programIds() {
        Set<Integer> result = new HashSet<>(rightHandSide);
        result.add(Integer.valueOf(leftHandSide));
        return result;
    }
}
