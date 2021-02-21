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
 * @param player1Deck Player 1's (our) hand
 * @param player2Deck Player 2's (the small crab's) hand
 * @param player1DeckHistory history of hands held by Player 1
 * @param player2DeckHistory history of hands held by Player 2
 * 
 * @author Martijn van de Rijdt
 */
record Game(List<Integer> player1Deck, List<Integer> player2Deck, List<List<Integer>> player1DeckHistory, List<List<Integer>> player2DeckHistory) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    
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
                .collect(Collectors.toList());
        
        List<Integer> player2Hand = IntStream.range(player2Index + 1, lines.size())
                .mapToObj(lines::get)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        return new Game(player1Hand, player2Hand, List.of(), List.of());
    }
    
    /**
     * Plays out the entire game.
     * 
     * @return the winning player's score
     */
    int play() {
        Game game = this;
        int round = 1;
        while (!game.player1Deck.isEmpty() && !game.player2Deck.isEmpty()) {
            game = game.playRound(round);
            round++;
        }
        
        LOGGER.debug("");
        LOGGER.debug("== Post-game results ==");
        game.logDecks();
        
        int result;
        if (game.player1Deck.isEmpty()) {
            LOGGER.debug("Player 2 wins!");
            result = game.player2Score();
        } else {
            LOGGER.debug("Player 1 wins!");
            result = game.player1Score();
        }
        return result;
    }

    /**
     * Plays a single round of the game.
     * 
     * This method assumes that the game has not yet concluded:
     * both players must still have at least one card in their deck.
     * 
     * @param round round number (for logging purposes)
     * @return updated game state after playing a single round
     */
    private Game playRound(int round) {
        LOGGER.debug("-- Round {} --", Integer.valueOf(round));
        logDecks();
        
        Integer player1Card = player1Deck.get(0);
        List<Integer> newPlayer1Deck = new ArrayList<>(player1Deck.subList(1, player1Deck.size()));
        LOGGER.debug("Player 1 plays: {}", player1Card);
        
        Integer player2Card = player2Deck.get(0);
        List<Integer> newPlayer2Deck = new ArrayList<>(player2Deck.subList(1, player2Deck.size()));
        LOGGER.debug("Player 2 plays: {}", player2Card);
        
        if (player2Card.intValue() < player1Card.intValue()) {
            LOGGER.debug("Player 1 wins the round!");
            newPlayer1Deck.add(player1Card);
            newPlayer1Deck.add(player2Card);
        } else {
            LOGGER.debug("Player 2 wins the round!");
            newPlayer2Deck.add(player2Card);
            newPlayer2Deck.add(player1Card);
        }
        
        LOGGER.debug("");

        List<List<Integer>> newPlayer1DeckHistory = new ArrayList<>(player1DeckHistory);
        newPlayer1DeckHistory.add(player1Deck);
        List<List<Integer>> newPlayer2DeckHistory = new ArrayList<>(player2DeckHistory);
        newPlayer1DeckHistory.add(player2Deck);
        
        return new Game(newPlayer1Deck, newPlayer2Deck, newPlayer1DeckHistory, newPlayer2DeckHistory);
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
