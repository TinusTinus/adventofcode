package nl.mvdr.adventofcode.adventofcode2020.day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a game of crab combat.
 *
 * @param recursive whether the rules of Recursive Combat should be used (that is, the rules from part 2 of the puzzle)
 * @param player1Deck Player 1's (our) hand
 * @param player2Deck Player 2's (the small crab's) hand
 * @param history previous game states
 * @param winner this game's winner; empty if the game has not yet concluded
 * 
 * @author Martijn van de Rijdt
 */
record Game(boolean recursive,
        List<Integer> player1Deck,
        List<Integer> player2Deck,
        List<Game> history,
        Optional<Player> winner) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    
    /**
     * Parses the puzzle input.
     * 
     * @param linesStream lines from the puzzle input
     * @param recursive whether the rules of Recursive Combat should be used (that is, the rules from part 2 of the puzzle)
     * @return initial state of the game represented by the puzzle input
     */
    static Game parse(Stream<String> linesStream, boolean recursive) {
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
        
        return new Game(recursive, player1Hand, player2Hand, List.of(), Optional.empty());
    }
    
    /**
     * Plays out the entire game.
     * 
     * @return the concluded game
     */
    Game play() {
        Game game = this;
        while (game.winner.isEmpty()) {
            game = game.playRound();
        }
        
        LOGGER.debug("");
        LOGGER.debug("== Post-game results ==");
        game.logDecks();
        LOGGER.debug("{} wins!", game.winner.orElseThrow());
        
        return game;
    }

    /**
     * Plays a single round of the game.
     * 
     * This method assumes that the game has not yet concluded:
     * both players must still have at least one card in their deck.
     * 
     * @return updated game state after playing a single round
     */
    private Game playRound() {
        winner.ifPresent(w -> { throw new IllegalStateException("This game has already concluded: " + this); });
        
        LOGGER.debug("-- Round {} --", Integer.valueOf(history.size() + 1));
        logDecks();
        
        Game result;
        if (recursive && detectInfiniteRecursion()) {
            LOGGER.debug("Infinite recursion detected.");
            // The game instantly ends in a win for Player 1. (This prevents infinite games of Recursive Combat.)
            result = new Game(recursive, player1Deck, player2Deck, history, Optional.of(Player.ONE));
        } else {
            Integer player1Card = player1Deck.get(0);
            List<Integer> newPlayer1Deck = new ArrayList<>(player1Deck.subList(1, player1Deck.size()));
            LOGGER.debug("Player 1 plays: {}", player1Card);

            Integer player2Card = player2Deck.get(0);
            List<Integer> newPlayer2Deck = new ArrayList<>(player2Deck.subList(1, player2Deck.size()));
            LOGGER.debug("Player 2 plays: {}", player2Card);

            Player roundWinner;
            if (recursive && player1Card.intValue() <= newPlayer1Deck.size() && player2Card.intValue() <= newPlayer2Deck.size()) {
                LOGGER.debug("Playing a sub-game to determine the winner...");
                LOGGER.debug("");
                List<Integer> player1SubDeck = newPlayer1Deck.subList(0, player1Card.intValue());
                List<Integer> player2SubDeck = newPlayer2Deck.subList(0, player2Card.intValue());
                Game subgame = new Game(true, player1SubDeck, player2SubDeck, List.of(), Optional.empty());
                roundWinner = subgame.play().winner.orElseThrow();
                LOGGER.debug("");
                LOGGER.debug("...anyway, back to the previous game.");
            } else if (player2Card.intValue() < player1Card.intValue()) {
                roundWinner = Player.ONE;
            } else {
                roundWinner = Player.TWO;
            }
            
            LOGGER.debug("{} wins the round!", roundWinner);
            
            if (roundWinner == Player.ONE) {
                newPlayer1Deck.add(player1Card);
                newPlayer1Deck.add(player2Card);
            } else if (roundWinner == Player.TWO) {
                newPlayer2Deck.add(player2Card);
                newPlayer2Deck.add(player1Card);
            } else {
                throw new IllegalStateException("Unexpected round winner: " + roundWinner);
            }

            List<Game> newHistory = new ArrayList<>(history);
            newHistory.add(this);

            Optional<Player> newWinner;
            if (newPlayer1Deck.isEmpty()) {
                newWinner = Optional.of(Player.TWO);
            } else if (newPlayer2Deck.isEmpty()) {
                newWinner = Optional.of(Player.ONE);
            } else {
                newWinner = Optional.empty();
            }

            result = new Game(recursive, newPlayer1Deck, newPlayer2Deck, newHistory, newWinner);
            LOGGER.debug("");
        }
        return result;
    }
    
    /** @return whether there was a previous round in this game that had exactly the same cards in the same order in the same players' decks */
    private boolean detectInfiniteRecursion() {
        return history.stream()
                .filter(historicGame -> historicGame.player1Deck.equals(player1Deck))
                .filter(historicGame -> historicGame.player2Deck.equals(player2Deck))
                .findAny()
                .isPresent();
    }
    
    /** Writes the game's current state to the {@link #LOGGER}. */
    private void logDecks() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Player 1's deck: {}", player1Deck.stream().map(Object::toString).collect(Collectors.joining(", ")));
            LOGGER.debug("Player 2's deck: {}", player2Deck.stream().map(Object::toString).collect(Collectors.joining(", ")));
        }
    }
    
    /**
     * Returns the winning player's score.
     * 
     * Note that this method only works for games which have concluded!
     * That is, the result of {@link #play()}.
     * 
     * @return the winning player's score
     */
    int winnerScore() {
        return score(winner.orElseThrow(() -> new IllegalStateException("This game has not yet concluded: " + this)));
    }
    
    /**
     * Gives the given player's score.
     * 
     * @param player player
     * @return score
     */
    private int score(Player player) {
        List<Integer> deck;
        if (player == Player.ONE) {
            deck = player1Deck;
        } else if (player == Player.TWO) {
            deck = player2Deck;
        } else {
            throw new IllegalArgumentException("Unexpected player: " + player);
        }
        
        return score(deck);
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
