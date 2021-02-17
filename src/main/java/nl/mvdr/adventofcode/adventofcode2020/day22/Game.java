package nl.mvdr.adventofcode.adventofcode2020.day22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
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
    private final Deque<Integer> player1Deck;
    
    /** Player 2's (the small crab's) hand. */
    private final Deque<Integer> player2Deck;

    /**
     * Constructor.
     * 
     * @param player1Deck player 1's initial hand
     * @param player2Deck player 2's initial hand
     */
    private Game(Deque<Integer> player1Deck, Deque<Integer> player2Deck) {
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
        
        Deque<Integer> player1Hand = IntStream.range(1, player2Index)
                .mapToObj(lines::get)
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayDeque::new));
        
        Deque<Integer> player2Hand = IntStream.range(player2Index + 1, lines.size())
                .mapToObj(lines::get)
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayDeque::new));
        
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
            LOGGER.info("-- Round {} --", Integer.valueOf(round));
            logDecks();
            
            Integer player1Card = player1Deck.pop();
            LOGGER.info("Player 1 plays: {}", player1Card);
            
            Integer player2Card = player2Deck.pop();
            LOGGER.info("Player 2 plays: {}", player2Card);
            
            if (player2Card.intValue() < player1Card.intValue()) {
                LOGGER.info("Player 1 wins the round!");
                player1Deck.addLast(player1Card);
                player1Deck.addLast(player2Card);
            } else {
                LOGGER.info("Player 2 wins the round!");
                player2Deck.addLast(player2Card);
                player2Deck.addLast(player1Card);
            }
            
            LOGGER.info("");
            
            round++;
        }
        
        LOGGER.info("");
        LOGGER.info("== Post-game results ==");
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
        LOGGER.info("Player 1's deck: {}", player1Deck.stream().map(Object::toString).collect(Collectors.joining(", ")));
        LOGGER.info("Player 2's deck: {}", player2Deck.stream().map(Object::toString).collect(Collectors.joining(", ")));
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
    private static int score(Deque<Integer> deck) {
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
