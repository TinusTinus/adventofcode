package nl.mvdr.adventofcode.adventofcode2017.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a "program" in the tower.
 *
 * @author Martijn van de Rijdt
 */
class Program {
    /** Name of this program. */
    private final String name;
    
    /** Weight of this program. */
    private final int weight;
    
    /** Names of the programs held up by this one. */
    private final List<String> namesHeldUp;

    /**
     * Parses the puzzle input into a collection of programs.
     * 
     * @param inputFilePath path to the text file containing the input
     * @return collection of programs
     * @throws IOException exception indicating that the input text file could not be read
     */
    static List<Program> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(Program::parseLine)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single line from the puzzle input into a program.
     * 
     * @param line text
     * @return program
     */
    private static Program parseLine(String line) {
        String[] parts = line.split("\\s");
        
        String name = parts[0];
        
        int weight = Integer.parseInt(parts[1].substring(1, parts[1].length() - 1));
        
        List<String> namesHeldUp = Stream.of(parts)
                .skip(3) // name, weight, arrow
                .map(part -> part.replace(",", "")) // drop the commas
                .collect(Collectors.toList());
        
        return new Program(name, weight, namesHeldUp);
    }
    
    /**
     * Constructor.
     * 
     * @param name name of this program
     * @param weight weight of this program
     * @param namesHeldUp names of the programs held up by this one
     */
    private Program(String name, int weight, List<String> namesHeldUp) {
        super();
        this.name = name;
        this.weight = weight;
        this.namesHeldUp = namesHeldUp;
    }
    
    String getName() {
        return name;
    }
    
    List<String> getNamesHeldUp() {
        return namesHeldUp;
    }
    
    int getWeight() {
        return weight;
    }
    
    /**
     * Checks whether this program is (directly) holding up the program with the given name.
     * 
     * @param name program name
     * @return whether this program is (directly) holding up the program with the given name
     */
    boolean isHoldingUp(String name) {
        return namesHeldUp.contains(name);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append(name);
        builder.append(" (");
        builder.append(weight);
        builder.append(")");
        if (!namesHeldUp.isEmpty()) {
            builder.append(" -> ");
            builder.append(namesHeldUp.stream().collect(Collectors.joining(", ")));
        }
        
        return builder.toString();
    }
}
