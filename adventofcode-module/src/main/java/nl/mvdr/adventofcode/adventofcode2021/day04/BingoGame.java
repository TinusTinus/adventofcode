package nl.mvdr.adventofcode.adventofcode2021.day04;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of a game of bingo.
 *
 * @author Martijn van de Rijdt
 */
record BingoGame(List<Integer> numbers, Set<BingoCard> cards) {
    
    /**
     * Parses the puzzle input into a game of bingo.
     * 
     * @param lines puzzle input
     * @return game
     */
    static BingoGame parse(List<String> lines) {
        // The first line contains the drawn numbers, separated by commas.
        var numbersLine = lines.get(0);
        var numbers = Stream.of(numbersLine.split(","))
                .map(Integer::valueOf)
                .toList();
        
        // Starting from line 2, the puzzle input consists of bingo cards, separated by an extra empty line.
        var bingoCards = IntStream.range(0, Math.divideExact(lines.size() - 2, (BingoCard.SIZE + 1)) + 1)
                .map(bingoCardIndex -> 2 + bingoCardIndex * (BingoCard.SIZE + 1))
                .mapToObj(startIndex -> lines.subList(startIndex, startIndex + BingoCard.SIZE))
                .map(BingoCard::parse)
                .collect(Collectors.toSet());
        
        return new BingoGame(numbers, bingoCards);
    }
    
    /**
     * @return winning score of this game
     */
    int calculateWinningScore() {
        var numbersDrawn = 0;
        OptionalInt result = OptionalInt.empty();
        while (result.isEmpty()) {
            numbersDrawn++;
            var drawnNumbers = draw(numbersDrawn);
            result = cards.stream()
                    .filter(card -> card.bingo(drawnNumbers))
                    .mapToInt(card -> card.score(drawnNumbers))
                    .max();
        }
        return result.orElseThrow();
    }

    /**
     * Draws the given number of bingo numbers.
     * 
     * @param n numbers to draw
     * @return drawn numbers
     */
    private List<Integer> draw(int n) {
        return numbers.subList(0, n);
    }
    
    /**
     * @return score of the last board to win
     */
    int calculateLastWinnerScore() {
        var remainingCards = cards;
        var numbersDrawn = 0;
        while (1 < remainingCards.size()) {
            numbersDrawn++;
            var drawnNumbers = draw(numbersDrawn);
            remainingCards = remainingCards.stream()
                    .filter(Predicate.not(card -> card.bingo(drawnNumbers)))
                    .collect(Collectors.toSet());
        }
        var lastCard = remainingCards.iterator().next();
        while (!lastCard.bingo(draw(numbersDrawn))) {
            numbersDrawn++;
        }
        return lastCard.score(draw(numbersDrawn));
    }
}
