package nl.mvdr.adventofcode.adventofcode2018.day09;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
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
public class MarbleManiaPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MarbleManiaPart1.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        PuzzleInput puzzleInput = PuzzleInput.parse(inputFilePath);
        
        int[] scores = new int[puzzleInput.getPlayers()];
        List<Integer> marbles = new ArrayList<>(List.of(Integer.valueOf(0)));
        int currentMarbleIndex = 0;
        
        for (int marble = 1; marble != puzzleInput.getPoints(); marble++) {
            int playerIndex = marble % puzzleInput.getPlayers();
            if (marble % 23 == 0) {
                scores[playerIndex] += marble;
                currentMarbleIndex = (currentMarbleIndex - 7 + marbles.size()) % marbles.size();
                scores[playerIndex] += marbles.remove(currentMarbleIndex).intValue();
            } else {
                currentMarbleIndex = (currentMarbleIndex + 2) % marbles.size() + 1;
                marbles.add(currentMarbleIndex, Integer.valueOf(marble));
            }
            
            logGameState(puzzleInput, marbles, currentMarbleIndex, playerIndex);
            
        }
        
        return "" + IntStream.of(scores).max().getAsInt();
    }

    private void logGameState(PuzzleInput puzzleInput, List<Integer> marbles, int currentMarbleIndex, int playerIndex) {
        if (LOGGER.isDebugEnabled()) {

            StringBuilder builder = new StringBuilder("[");
            builder.append(String.format("%" + ("" + puzzleInput.getPlayers()).length() + "d", playerIndex + 1));
            builder.append("] ");
            for (int i = 0; i != marbles.size(); i++) {
                if (i == currentMarbleIndex) {
                    builder.append("(");
                } else {
                    builder.append(" ");
                }
                builder.append(String.format("%" + ("" + puzzleInput.getPoints()).length() + "d", marbles.get(i)));
                if (i == currentMarbleIndex) {
                    builder.append(")");
                } else {
                    builder.append(" ");
                }
            }
            LOGGER.debug(builder.toString());
        }
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MarbleManiaPart1 instance = new MarbleManiaPart1();

        String result = instance.solve("input-day09-2018.txt");

        LOGGER.info(result);
    }
}
