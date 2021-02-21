package nl.mvdr.adventofcode.adventofcode2020.day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a game of crab combat.
 * 
 * Note that this class is <em>mutable</em>.
 *
 * @author Martijn van de Rijdt
 */
class Game {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    
    /** Player 1's (our) hand. */
    private final List<Integer> player1Deck;
    
    /** Player 2's (the small crab's) hand. */
    private final List<Integer> player2Deck;

    /**
     * Constructor.
     * 
     * @param player1Deck player 1's initial hand
     * @param player2Deck player 2's initial hand
     */
    private Game(List<Integer> player1Deck, List<Integer> player2Deck) {
        super();
        this.player1Deck = player1Deck;
        this.player2Deck = player2Deck;
    }
    
    /**
     * Parses the puzzle input.
     * 
     * @param linesStream lines from the puzzle input
     * @return initial state of the game represented by the puzzle input
     */
    static Game parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        int player2Index = lines.indexOf("Player 2:");
        
        List<Integer> player1Hand = IntStream.range(1, player2Index)
                .mapToObj(lines::get)
                .map(Integer::valueOf)
                .collect(Collectors.toList()); // TODO refactor: Collectors.toList() is not guaranteed to return a mutable list type
        
        List<Integer> player2Hand = IntStream.range(player2Index + 1, lines.size())
                .mapToObj(lines::get)
                .map(Integer::valueOf)
                .collect(Collectors.toList()); // TODO refactor: Collectors.toList() is not guaranteed to return a mutable list type
        
        return new Game(player1Hand, player2Hand);
    }
    
    /**
     * Plays out the entire game.
     * 
     * @return the winning player's score
     */
    int play() {
        int round = 1;
        while (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            LOGGER.debug("-- Round {} --", Integer.valueOf(round));
            logDecks();
            
            Integer player1Card = player1Deck.remove(0);
            LOGGER.debug("Player 1 plays: {}", player1Card);
            
            Integer player2Card = player2Deck.remove(0);
            LOGGER.debug("Player 2 plays: {}", player2Card);
            
            if (player2Card.intValue() < player1Card.intValue()) {
                LOGGER.debug("Player 1 wins the round!");
                player1Deck.add(player1Card);
                player1Deck.add(player2Card);
            } else {
                LOGGER.debug("Player 2 wins the round!");
                player2Deck.add(player2Card);
                player2Deck.add(player1Card);
            }
            
            LOGGER.debug("");
            
            round++;
        }
        
        LOGGER.debug("");
        LOGGER.debug("== Post-game results ==");
        logDecks();
        
        int result;
        if (player1Deck.isEmpty()) {
            result = player2Score();
        } else {
            result = player1Score();
        }
        return result;
    }
    
    /** Writes the game's current state to the {@link #LOGGER}. */
    private void logDecks() {
        LOGGER.debug("Player 1's deck: {}", player1Deck.stream().map(Object::toString).collect(Collectors.joining(", ")));
        LOGGER.debug("Player 2's deck: {}", player2Deck.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }
    
    /** @return player 1's score */
    private int player1Score() {
        return score(player1Deck);
    }
    
    /** @return player 2's score */
    private int player2Score() {
        return score(player2Deck);
    }
    
    /**
     * Computes the score for the given deck.
     * 
     * @param deck deck
     * @return score
     */
    private static int score(List<Integer> deck) {
        int result = 0;
        int i = 1;
        
        List<Integer> reverseDeck = new ArrayList<>(deck);
        Collections.reverse(reverseDeck);
        
        for (Integer card : reverseDeck) {
            result = result + i * card.intValue();
            i++;
        }
        return result;
    }
}
