package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Races of units.
 *
 * @author Martijn van de Rijdt
 */
enum Race {
    /** A goblin. */
    GOBLIN('G'),
    /** An elf. */
    ELF('E');
    
    /** Character representation of this race in the puzzle input. */
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of this race in the puzzle input
     */
    Race(char representation) {
        this.representation = representation;
    }
    
    /**
     * Parses the given character into a race.
     * 
     * @param representation character representation
     * @return corresponding race; empty if none apply
     */
    static Optional<Race> of(char representation) { 
        return Stream.of(Race.values())
                .filter(race -> race.representation == representation)
                .findFirst();
    }
}
