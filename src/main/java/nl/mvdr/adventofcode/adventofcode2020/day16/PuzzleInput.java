package nl.mvdr.adventofcode.adventofcode2020.day16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Notes on train tickets.
 *
 * @author Martijn van de Rijdt
 */
record PuzzleInput(Map<String, List<ValueRange>> rules, Ticket myTicket, List<Ticket> nearbyTickets) {
    /**
     * Parses the puzzle input.
     * 
     * @param linesStream lines from the puzzle input
     * @return contents of the puzzle input
     */
    static PuzzleInput parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        int yourTicketHeaderIndex = lines.indexOf("your ticket:");
        if (!"nearby tickets:".equals(lines.get(yourTicketHeaderIndex + 2))) {
            throw new IllegalArgumentException("Invalid input: " + lines);
        }
        
        Map<String, List<ValueRange>> rules = parseRules(lines.subList(0, yourTicketHeaderIndex));
        Ticket myTicket = Ticket.parse(lines.get(yourTicketHeaderIndex + 1));
        List<Ticket> nearbyTickets = lines.subList(yourTicketHeaderIndex + 3, lines.size()).stream()
                .map(Ticket::parse)
                .collect(Collectors.toList());
        
        return new PuzzleInput(rules, myTicket, nearbyTickets);
    }
    
    /**
     * Parses ticket field rules from the puzzle input.
     * 
     * @param lines lines from the puzzle input where each line represents a rule, for example: {@code class: 1-3 or 5-7}
     * @return ticket field rules
     */
    private static Map<String, List<ValueRange>> parseRules(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(": "))
                .peek(parts -> {
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid rule consisting of parts: " + Arrays.toString(parts));
                    }
                })
                .collect(Collectors.toMap(parts -> parts[0], parts -> ValueRange.parse(parts[1])));
    }
    
    /** @return sum of the nearby ticket values which are not valid for any field */
    int sumInvalidValues() {
        return nearbyTickets.stream()
                .map(Ticket::fields)
                .flatMap(List::stream)
                .mapToInt(Integer::valueOf)
                .filter(value -> !isValidValue(value))
                .sum();
    }
    
    /** @return product of the values of the six departure fields on our ticket */
    int multiplyDepartureFields() {
        List<String> fieldNames = determineFieldOrder();
        
        return IntStream.range(0, fieldNames.size())
                .filter(i -> fieldNames.get(i).startsWith("departure"))
                .map(i -> myTicket.fields().get(i).intValue())
                .reduce((i, j) -> i * j)
                .orElseThrow();
    }
    
    /** @return field names, in the correct order */
    private List<String> determineFieldOrder() {
        List<Ticket> validNearbyTickets = nearbyTickets.stream()
                .filter(ticket -> ticket.fields().stream().mapToInt(Integer::valueOf).allMatch(this::isValidValue))
                .collect(Collectors.toList());
        
        List<Set<String>> possibleFieldNames = IntStream.range(0, myTicket.fields().size())
                .mapToObj(i -> determinePossibleFieldNames(i, validNearbyTickets))
                .collect(Collectors.toList());
        
        while (possibleFieldNames.stream().anyMatch(set -> 1 < set.size())) {
            IntStream.range(0, possibleFieldNames.size())
                    .filter(i -> possibleFieldNames.get(i).size() == 1)
                    .forEach(i -> 
                            IntStream.range(0, possibleFieldNames.size())
                            .filter(j -> j != i)
                            .forEach(j -> possibleFieldNames.get(j).remove(possibleFieldNames.get(i).iterator().next()))
                    );
        }
        
        return possibleFieldNames.stream()
                .map(set -> set.iterator().next())
                .collect(Collectors.toList());
    }
    
    /**
     * Determines the possible field names for the given field index, based on nearby ticket values.
     * 
     * @param fieldIndex field index
     * @param validNearbyTickets valid nearby tickets
     * @return a mutable (!) set containing possible field names
     */
    private Set<String> determinePossibleFieldNames(int fieldIndex, List<Ticket> validNearbyTickets) {
        Set<Integer> values = validNearbyTickets.stream()
                .map(ticket -> ticket.fields().get(fieldIndex))
                .collect(Collectors.toSet());
        
        return rules.entrySet()
                .stream()
                .filter(entry -> areAllValid(values, entry.getValue()))
                .map(Entry::getKey)
                .collect(Collectors.toCollection(HashSet::new));
    }
    
    /**
     * Checks whether all of the given values satisfy the given rule.
     * 
     * @param values values to validate
     * @param rule value ranges of the rule
     * @return whether all of the given values satisfy the given rule
     */
    private boolean areAllValid(Set<Integer> values, List<ValueRange> rule) {
        return values.stream()
                .mapToInt(Integer::intValue)
                .allMatch(value -> rule.stream().anyMatch(range -> range.contains(value)));
    }

    /**
     * Determines whether the given value is valid according to any of the rules.
     * 
     * @param value value to validate
     * @return whether the given value is valid for the given rules
     */
    private boolean isValidValue(int value) {
        return rules.values()
                .stream()
                .flatMap(List::stream)
                .anyMatch(range -> range.contains(value));
    }
}
