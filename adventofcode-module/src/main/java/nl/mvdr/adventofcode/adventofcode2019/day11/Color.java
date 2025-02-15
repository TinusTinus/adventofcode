package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.stream.Stream;

/**
 * Color values that panels of the hull can have.
 *
 * @author Martijn van de Rijdt
 */
enum Color {
    /** Black. Initial color of the entire hull. */
    BLACK(0L),

    /** White. */
    WHITE(1L);

    private final long code;

    /**
     * Converts a code to a color.
     * 
     * @param code representation of a color in the robot's Intcode program
     * @return color
     */
    static Color of(long code) {
        return Stream.of(Color.values())
                .filter(value -> value.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown color code: " + code));
    }

    /**
     * Constructor.
     * 
     * @param code representation of this color in the robot's Intcode program
     */
    Color(long code) {
        this.code = code;
    }
    
    /** @return representation of this color in the robot's Intcode program */
    long getCode() {
        return code;
    }
}
