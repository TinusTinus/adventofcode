package nl.mvdr.adventofcode.adventofcode2018.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object representation of the puzzle input.
 *
 * @author Martijn van de Rijdt
 */
class PuzzleInput {
    private static final Pattern PATTERN = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points");
    
    private final int players;
    private final int points;
    
    private PuzzleInput(int players, int points) {
        super();
        this.players = players;
        this.points = points;
    }
    
    /** @return the number of players */
    int getPlayers() {
        return players;
    }
    
    /** @return point value of the last marble */
    int getPoints() {
        return points;
    }
    
    /**
     * Parses the given puzzle input.
     * 
     * @param inputFilePath path of the input text file
     * @return puzzle input
     * @throws IOException in case the input file could not be read
     */
    static PuzzleInput parse(Path inputFilePath) throws IOException {
        String line = Files.lines(inputFilePath).findFirst().get();
        
        Matcher matcher = PATTERN.matcher(line);
        matcher.matches();
        int players = Integer.parseInt(matcher.group(1));
        int points = Integer.parseInt(matcher.group(2));
        
        return new PuzzleInput(players, points);
    }
    
    @Override
    public String toString() {
        return String.format("%s players; last marble is worth %s points", players, points);
    }
}
