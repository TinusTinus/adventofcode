package nl.mvdr.adventofcode.adventofcode2017.day12;

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
     * @param input puzzle input
     * @return groups
     */
    static Set<Set<Integer>> parse(Stream<String> input) {
        List<Line> lines = parseLines(input);
        return group(lines);
    }

    /**
     * Parses the puzzle input.
     * 
     * @param input puzzle input
     * @return lines
     */
    private static List<Line> parseLines(Stream<String> input) {
        return input
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
     * Groups the program ids in the given lines.
     * 
     * @param lines puzzle input lines
     * @return groups
     */
    private static Set<Set<Integer>> group(List<Line> lines) {
        Set<Set<Integer>> groups = new HashSet<>();
        
        for (Line line : lines) {
            Set<Integer> programIds = line.programIds();
            
            Set<Integer> newGroup = new HashSet<>(programIds);
            
            for (Integer programId : programIds) {
                groups.stream()
                        .filter(group -> group.contains(programId))
                        .findFirst()
                        .ifPresent(oldGroup -> {
                            groups.remove(oldGroup);
                            newGroup.addAll(oldGroup);
                        });
            }
            
            groups.add(newGroup);
        }
        return groups;
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
