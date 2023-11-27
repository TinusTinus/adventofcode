package nl.mvdr.adventofcode.adventofcode2021.day04;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of a single bingo card.
 *
 * @author Martijn van de Rijdt
 */
record BingoCard(List<List<Integer>> rows) {
    /** Size of a bingo card, that is, the length of each row and column. */
    static final int SIZE = 5;

    /**
     * Parses a single bingo card.
     * 
     * @param lines the five lines representing a bingo card
     * @return bingo card
     */
    static BingoCard parse(List<String> lines) {
        if (lines.size() != SIZE) {
            throw new IllegalArgumentException("Unable to parse as bingo card: " + lines);
        }
        var rows = lines.stream()
                .map(BingoCard::parseRow)
                .toList();
        return new BingoCard(rows);
    }
    
    /**
     * Parses a single row of a bingo card.
     * 
     * @param line string representation of a row
     * @return row of a bingo card
     */
    private static List<Integer> parseRow(String line) {
        var parts = line.trim().split(" +");
        if (parts.length != SIZE) {
            throw new IllegalArgumentException("Unable to parse line: " + line);
        }
        return Stream.of(parts)
                .map(Integer::valueOf)
                .toList();
    }
    
    /**
     * @return columns of this card
     */
    List<List<Integer>> columns() {
        return IntStream.range(0, SIZE)
                .mapToObj(columnIndex -> IntStream.range(0, SIZE)
                        .mapToObj(rowIndex -> rows.get(rowIndex))
                        .map(row -> row.get(columnIndex))
                        .toList())
                .collect(Collectors.toList());
    }
}
