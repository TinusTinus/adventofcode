package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A dance move.
 *
 * @author Martijn van de Rijdt
 * 
 */
interface DanceMove {
    
    /**
     * Parses the given input file to a list of dance moves.
     * 
     * @param inputFilePath path to the input text file
     * @return list of moves
     * @throws IOException if the file could not be read
     */
    static List<DanceMove> parse(Path inputFilePath) throws IOException {
        String text = Files.lines(inputFilePath).findFirst().get();
        return Stream.of(text.split(","))
                .map(DanceMove::parse)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single dance move.
     * 
     * @param text text to be parsed
     * @return dance move represented by the given text
     */
    private static DanceMove parse(String text) {
        DanceMove result;
        if (text.startsWith("s")) {
            int numberOfDancers = Integer.parseInt(text.substring(1));
            result = new Spin(numberOfDancers);
        } else {
            String[] parameters = text.substring(1).split("/");
            if (text.startsWith("x")) {
                int a = Integer.parseInt(parameters[0]);
                int b = Integer.parseInt(parameters[1]);
                result = new Exchange(a, b);
            } else if (text.startsWith("p")) {
                char a = parameters[0].charAt(0);
                char b = parameters[1].charAt(0);
                result = new Partner(a, b);
            } else {
                throw new IllegalArgumentException("Unknown dance move: " + text);
            }
        }
        return result;
    }
    
    /**
     * Lets the given dancers perform this dance move.
     * 
     * Note that this method does not modify the given list, but rather returns a new list.
     * 
     * @param dancers dancers
     * @return new list representing the order of the dances after performing the dance move
     */
    List<Program> perform(List<Program> dancers);
}
