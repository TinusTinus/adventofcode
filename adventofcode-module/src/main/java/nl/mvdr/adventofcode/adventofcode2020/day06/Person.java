package nl.mvdr.adventofcode.adventofcode2020.day06;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representation of a person, including the questions they have answered with "yes".
 *
 * @param questions the questions to which this person answered "yes"
 *
 * @author Martijn van de Rijdt
 */
record Person(Set<Character> questions) {
    /**
     * Parses a textual representation of a person.
     * 
     * @param text textual representation of a person
     * @return person
     */
    static Person parse(String text) {
        Set<Character> questions = text.chars()
                .mapToObj(i -> Character.valueOf((char)i))
                .collect(Collectors.toSet());
        return new Person(questions);
    }
}
