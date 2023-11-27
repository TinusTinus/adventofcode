package nl.mvdr.adventofcode.adventofcode2021.day04;

import java.util.List;
import java.util.Set;
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
        var bingoCards = IntStream.range(0, Math.divideExact(lines.size() - 2, (BingoCard.SIZE + 1)))
                .map(bingoCardIndex -> 2 + bingoCardIndex * (BingoCard.SIZE + 1))
                .mapToObj(startIndex -> lines.subList(startIndex, startIndex + BingoCard.SIZE))
                .map(BingoCard::parse)
                .collect(Collectors.toSet());
        
        return new BingoGame(numbers, bingoCards);
    }
}
