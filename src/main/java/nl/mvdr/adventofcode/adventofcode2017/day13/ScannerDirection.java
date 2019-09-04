package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

/**
 * A direction in which a scanner can move.
 *
 * @author Martijn van de Rijdt
 */
public enum ScannerDirection {
    BACK(i -> i - 1),
    FORTH(i -> i + 1);
    
    private final IntUnaryOperator next;
    
    ScannerDirection(IntUnaryOperator next) {
        this.next = next;
    }
    
    /**
     * Moves the scanner position one step in this direction.
     * 
     * @param scannerPosition current scanner position
     * @return next scanner position
     */
    int apply(int scannerPosition) {
        return next.applyAsInt(scannerPosition);
    }
    
    /** @return the other direction (not this one) */
    ScannerDirection other() {
        return Stream.of(values())
                .filter(value -> this != value)
                .findAny()
                .get();
    }
}
