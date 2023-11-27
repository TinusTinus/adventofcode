package nl.mvdr.adventofcode.adventofcode2020.day23;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State of the game.
 * 
 * Note: this class is <em>not</em> immutable: the {@link #perform(int)} method modifies the state of the {@link #cups}.
 * Do not rely on {@link #equals(Object)} and {@link #hashCode()}.
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
        List<Integer> cups = new LinkedList<>(parseCups(lines));
        
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
        List<Integer> cups = new LinkedList<>();
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
        List<Integer> orderedCups = new LinkedList<>();
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
     */
    void perform(int moves) {
        for (int move = 0; move != moves; move++) {
            if (move % 10_00 == 9_999) {
                LOGGER.info("-- move {} --", Integer.valueOf(move + 1));
            } else {
                LOGGER.debug("-- move {} --", Integer.valueOf(move + 1));
            }
            move();
        }
    }
    
    /** Performs a single move. */
    private void move() {
        LOGGER.debug("cups: {}", this);
        
        int current = cups.remove(0).intValue();
        
        List<Integer> pickup = new LinkedList<>(cups.subList(0, 3));
        IntStream.range(0, 3).forEach(i -> cups.remove(0));
        LOGGER.debug("pickup: {}", pickup);
        
        int destination = current - 1;
        if (destination == 0) {
            destination = cups.size() + pickup.size() + 1;
        }
        while (pickup.contains(Integer.valueOf(destination))) {
            destination--;
            if (destination == 0) {
                destination = cups.size() + pickup.size() + 1;
            }
        }
        LOGGER.debug("destination: " + destination);

        int indexOfDestination = cups.indexOf(Integer.valueOf(destination)); // note: this indexof is expensive, we could use some administration to prevent this lookup
        cups.addAll(indexOfDestination + 1, pickup);
        cups.add(Integer.valueOf(current));
    }
    
    /**
     * @return product of the labels of the two cups after the one labeled "1"
     */
    long productOfCupsContainingStars() {
        int indexOf1 = cups.indexOf(Integer.valueOf(1));
        long nextValue = cups.get((indexOf1 + 1) % cups.size()).longValue();
        long nextNextValue = cups.get((indexOf1 + 2) % cups.size()).longValue();
        return nextValue * nextNextValue;
    }
}
