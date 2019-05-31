package nl.mvdr.adventofcode.adventofcode2018.day09;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to part 1 of the day 9 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/9">Marble Mania</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MarbleMania implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MarbleMania.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        PuzzleInput puzzleInput = PuzzleInput.parse(inputFilePath);
        
        int[] scores = new int[puzzleInput.getPlayers()];
        LinkedList<Integer> marbles = new LinkedList<>(List.of(Integer.valueOf(0)));
        
        for (int marble = 1; marble != puzzleInput.getPoints(); marble++) {
            int playerIndex = marble % puzzleInput.getPlayers();
            if (marble % 23 == 0) {
                for (int i = 0; i != 6; i++) {
                    marbles.push(marbles.removeLast());
                }
                int removedMarble = marbles.removeLast();
                int points = marble + removedMarble;
                scores[playerIndex] += points;
            } else {
                marbles.add(marbles.pop());
                marbles.add(marbles.pop());
                marbles.push(Integer.valueOf(marble));
            }
            
            logGameState(marble, puzzleInput, marbles, playerIndex);
        }
        
        return "" + IntStream.of(scores).max().getAsInt();
    }

    private void logGameState(int marble, PuzzleInput puzzleInput, List<Integer> marbles, int playerIndex) {
        if (LOGGER.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder("[");
            // player number (1-based rather than the 0-based index)
            builder.append(String.format("%" + ("" + puzzleInput.getPlayers()).length() + "d", playerIndex + 1));
            builder.append("] ");
            // marbles
            for (int i = 0; i != marbles.size(); i++) {
                if (i == 0) {
                    // mark the current marble with round brackets
                    builder.append("(");
                } else {
                    builder.append(" ");
                }
                builder.append(String.format("%" + ("" + puzzleInput.getPoints()).length() + "d", marbles.get(i)));
                if (i == 0) {
                    // mark the current marble with round brackets
                    builder.append(")");
                } else {
                    builder.append(" ");
                }
            }
            LOGGER.debug(builder.toString());
        }
        
        if (marble % 100_000 == 0) {
            LOGGER.info("  Progress: " + marble + " / " + puzzleInput.getPoints() + " (" + marble * 100 / puzzleInput.getPoints() + "%)");
        }
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MarbleMania instance = new MarbleMania();

        LOGGER.info("Part 1: " + instance.solve("input-day09-2018.txt"));
        LOGGER.info("Part 2: " + instance.solve("input-day09-2018-2.txt"));
    }
}
