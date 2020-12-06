package nl.mvdr.adventofcode.adventofcode2020.day05;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An airplane boarding pass, containing a reference to a seat identified by a row and a column.
 *
 * @author Martijn van de Rijdt
 */
record BoardingPass(int row, int column) {
    
    /**
     * Parses boarding passes.
     * 
     * @param lines puzzle input
     * @return boarding passes represented by the puzzle input
     */
    static List<BoardingPass> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isEmpty))
                .map(BoardingPass::parse)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a string representation of a boarding pass.
     * 
     * @param stringRepresentation string representation of a boarding pass
     * @return newly created boarding pass
     */
    static BoardingPass parse(String stringRepresentation) {
        String binaryRow = stringRepresentation.substring(0, 7)
                .replace('F', '0')
                .replace('B', '1');
        int row = Integer.parseInt(binaryRow, 2);
        
        String binaryColumn = stringRepresentation.substring(7)
                .replace('L', '0')
                .replace('R', '1');
        int column = Integer.parseInt(binaryColumn, 2);
        
        return new BoardingPass(row, column);
    }
    
    /** @return unique identification of this boarding pass's seat */
    int seatId() {
        return row * 8 + column;
    }
}
