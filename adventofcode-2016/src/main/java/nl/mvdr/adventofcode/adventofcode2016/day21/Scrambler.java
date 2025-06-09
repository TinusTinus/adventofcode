package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record Scrambler(List<ScramblerOperation> operations) implements Function<String, String> {
    
    static Scrambler parse(Stream<String> lines) {
        var operations = lines.map(ScramblerOperation::parse)
                .toList();
        return new Scrambler(operations);
    }
    
    @Override
    public String apply(String input) {
        Set<String> strings = Set.of(input);
        
        for (var operation : operations) {
            strings = strings.stream()
                    .flatMap(operation::apply)
                    .collect(Collectors.toSet());
        }
        
        if (strings.size() != 1) {
            throw new IllegalStateException("Expected exactly 1 result, found: " + strings);
        }
        
        return strings.iterator().next();
    }
    
    Scrambler reverse() {
        var reversedOperations = operations.reversed()
                .stream()
                .map(ScramblerOperation::reverse)
                .toList();
        return new Scrambler(reversedOperations);
    }
}
