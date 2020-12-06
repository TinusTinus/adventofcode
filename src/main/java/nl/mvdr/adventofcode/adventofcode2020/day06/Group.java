package nl.mvdr.adventofcode.adventofcode2020.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A group of people.
 *
 * @author Martijn van de Rijdt
 */
record Group(List<Person> people) {
    
    /**
     * Parses groups of people.
     * 
     * @param linesStream puzzle input
     * @return groups represented by the puzzle input
     */
    static List<Group> parse(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        
        List<Group> result = new ArrayList<>();
        
        List<Person> people = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                result.add(new Group(people));
                people = new ArrayList<>();
            } else {
                people.add(Person.parse(line));
            }
        }
        
        if (!people.isEmpty()) {
            result.add(new Group(people));
        }
        
        return result;
    }
    
    /** @return questions answered with "yes" by any member of this group */
    Set<Character> questions() {
        return people.stream()
                .map(Person::questions)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
