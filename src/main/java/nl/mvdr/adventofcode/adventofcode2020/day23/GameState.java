package nl.mvdr.adventofcode.adventofcode2020.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State of the game.
 *
 * @author Martijn van de Rijdt
 * 
 * @param cups the cups; first element of the list is the currently selected cup
 */
record GameState(List<Integer> cups) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GameState.class);
    
    /**
     * Parses a game state from the puzzle input.
     * 
     * @param lines lines from the puzzle input
     * @return game state represented by the input
     */
    static GameState parse(Stream<String> lines) {
        List<Integer> cups = lines.findFirst()
                .orElseThrow()
                .chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .boxed()
                .collect(Collectors.toList());
        
        return new GameState(cups);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(cups.get(0));
        builder.append(") ");
        builder.append(cups.stream()
                .skip(1)
                .map(i -> i.toString())
                .collect(Collectors.joining("  ")));
        return builder.toString();
    }
    
    /**
     * @return string representation of the order of the cups,
     * starting with the cup which is clockwise next to the cup labeled "1"
     */
    String getOrder() {
        int indexOf1 = cups.indexOf(Integer.valueOf(1));
        List<Integer> orderedCups = new ArrayList<>();
        orderedCups.addAll(cups.subList(indexOf1 + 1, cups.size()));
        orderedCups.addAll(cups.subList(0, indexOf1));
        
        return orderedCups.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining());
    }
    
    /**
     * Performs the given number of moves.
     * 
     * @param moves number of moves to perform
     * @return game state after performing the given number of moves
     */
    GameState perform(int moves) {
        GameState result = this;
        for (int move = 0; move != moves; move++) {
            LOGGER.debug("-- move {} --", move + 1);
            result = result.move();
        }
        return result;
    }
    
    
    /**
     * Performs a single move.
     * 
     * @return game state after performing a move
     */
    private GameState move() {
        LOGGER.debug("cups: {}", this);
        
        List<Integer> pickup = cups.subList(1, 4);
        LOGGER.debug("pickup: {}", pickup);
        
        int destination = cups.get(0).intValue() - 1;
        if (destination == 0) {
            destination = cups.size();
        }
        while (pickup.contains(Integer.valueOf(destination))) {
            destination--;
            if (destination == 0) {
                destination = cups.size();
            }
        }
        LOGGER.debug("destination: " + destination);

        List<Integer> newCups = new ArrayList<>(cups.size());
        newCups.addAll(cups.subList(4, cups.size()));
        int indexOfDestination = newCups.indexOf(Integer.valueOf(destination));
        newCups.addAll(indexOfDestination + 1, pickup);
        newCups.add(cups.get(0));
        return new GameState(newCups);
    }
}
