package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record Scrambler(List<ScramblerOperation> operations, boolean unscrambling) implements Function<String, String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Scrambler.class);
    
    static Scrambler parse(Stream<String> lines) {
        var operations = lines.map(ScramblerOperation::parse)
                .toList();
        return new Scrambler(operations, false);
    }
    
    @Override
    public String apply(String input) {
        Set<String> strings = Set.of(input);
        
        for (var operation : operations) {
            strings = strings.stream()
                    .flatMap(operation::apply)
                    .collect(Collectors.toSet());
        }
        
        if (strings.isEmpty()) {
            throw new IllegalArgumentException("Unable to " + (unscrambling ? "un" : "") + "scramble " + input);
        }
        
        if (2 <= strings.size()) {
            LOGGER.warn("Multiple possible results found while {}scrambling {}: {}", unscrambling ? "un" : "", input, strings);
        }
        
        return strings.stream().collect(Collectors.joining(" OR "));
    }
    
    Scrambler reverse() {
        var reversedOperations = operations.reversed()
                .stream()
                .map(ScramblerOperation::reverse)
                .toList();
        return new Scrambler(reversedOperations, !unscrambling);
    }
}
