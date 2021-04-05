package nl.mvdr.adventofcode.adventofcode2020.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
     * Parses a game state from the puzzle input,
     * containing only the cups specified in the input.
     * 
     * @param lines lines from the puzzle input
     * @return game state represented by the input
     */
    static GameState parse(Stream<String> lines) {
        List<Integer> cups = parseCups(lines);
        
        return new GameState(cups);
    }

    /**
     * Parses a game state from the puzzle input,
     * using the correct translation from Crab of the game rules.
     * 
     * (Part 2 of the puzzle.)
     * 
     * @param lines lines from the puzzle input
     * @return game state represented by the input
     */
    static GameState parseCorrectTranslation(Stream<String> lines) {
        List<Integer> cups = new ArrayList<>(1_000_000);
        cups.addAll(parseCups(lines));
        IntStream.range(cups.size(), 1_000_000)
            .boxed()
            .forEach(cups::add);
        return new GameState(cups);
    }
    
    /**
     * Parses the cups from the puzzle input.
     * 
     * @param lines puzzle input
     * @return cups specified by the puzzle input
     */
    private static List<Integer> parseCups(Stream<String> lines) {
        return lines.findFirst()
                .orElseThrow()
                .chars()
                .map(c -> Integer.parseInt("" + (char)c))
                .boxed()
                .collect(Collectors.toList());
    }

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(cups.get(0));
        builder.append(") ");
        builder.append(cups.stream()
                .skip(1)
                .limit(8)
                .map(i -> i.toString())
                .collect(Collectors.joining("  ")));
        if (9 < cups.size()) {
            builder.append(" ...");
        }
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
            LOGGER.debug("-- move {} --", Integer.valueOf(move + 1));
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
    
    /**
     * @return product of the labels of the cups containing the two stars
     */
    long productOfCupsContainingStars() {
        GameState endState = perform(10_000_000);
        int indexOf1 = endState.cups.indexOf(Integer.valueOf(1));
        
        long nextValue = endState.cups.get((indexOf1 + 1) % cups.size()).longValue();
        long nextNextValue = endState.cups.get((indexOf1 + 2) % cups.size()).longValue();
        return nextValue * nextNextValue;
    }
}
